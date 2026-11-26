(ns pipeline
  (:require [next.jdbc :as jdbc]
            [next.jdbc.result-set :as rs]
            [ring.adapter.jetty :refer [run-jetty]]
            [clojure.data.json :as json]))

(def db (jdbc/get-datasource {:dbtype "sqlite" :dbname "students.db"}))

(def raw-data
  [{:name "Ravi" :marks 82}
   {:name "Anu" :marks 91}
   {:name "Karan" :marks 45}
   {:name "Meena" :marks 67}
   {:name "Zoya" :marks 38}])

(defn grade [marks]
  (cond
    (>= marks 90) "A"
    (>= marks 75) "B"
    (>= marks 50) "C"
    :else "D"))

(def pass-xf
  (comp (filter #(>= (:marks %) 40))
        (map #(assoc % :grade (grade (:marks %))))))

(defn average-marks [rows]
  (if (empty? rows)
    0.0
    (double (/ (reduce + (map :marks rows)) (count rows)))))

(defn init-db! []
  (try
    (jdbc/execute! db ["CREATE TABLE IF NOT EXISTS students (
                          id INTEGER PRIMARY KEY AUTOINCREMENT,
                          name TEXT,
                          marks INTEGER,
                          grade TEXT)"])
    (println "Database ready")
    (catch Exception e
      (println "Database init error:" (.getMessage e)))))

(defn save-students! [rows]
  (try
    (jdbc/with-transaction [tx db]
      (jdbc/execute! tx ["DELETE FROM students"])
      (doseq [{:keys [name marks grade]} rows]
        (jdbc/execute! tx ["INSERT INTO students (name, marks, grade) VALUES (?, ?, ?)"
                           name marks grade])))
    (println "Saved" (count rows) "rows to database")
    true
    (catch Exception e
      (println "Database write error:" (.getMessage e))
      false)))

(defn load-students []
  (try
    (jdbc/execute! db
                   ["SELECT name, marks, grade FROM students"]
                   {:builder-fn rs/as-unqualified-maps})
    (catch Exception e
      (println "Database read error:" (.getMessage e))
      [])))

(defn json-response [data]
  {:status 200
   :headers {"Content-Type" "application/json"}
   :body (json/write-str data)})

(defn summary []
  (let [rows (load-students)]
    {:total-students (count rows)
     :average-marks (average-marks rows)
     :grade-counts (frequencies (map :grade rows))
     :top-student (when (seq rows)
                    (:name (apply max-key :marks rows)))
     :generated-at (str (java.time.LocalDateTime/now))
     :request-id (str (java.util.UUID/randomUUID))}))

(defn handler [request]
  (case (:uri request)
    "/students" (json-response (load-students))
    "/summary" (json-response (summary))
    {:status 404
     :headers {"Content-Type" "text/plain"}
     :body "Not found. Try /students or /summary"}))

(defn -main [& _]
  (init-db!)
  (let [processed (into [] pass-xf raw-data)]
    (println "Processed data (transducer):" processed)
    (println "Average marks (reduce):" (average-marks processed))
    (save-students! processed))
  (println "Rows read from database:" (load-students))
  (println "Server running at http://localhost:3000/students")
  (println "Summary at http://localhost:3000/summary")
  (run-jetty handler {:port 3000 :join? true}))