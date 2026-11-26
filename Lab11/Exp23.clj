(defn safe-divide [a b]
  (try
    (/ a b)
    (catch ArithmeticException e
      (println "ArithmeticException caught:" (.getMessage e))
      nil)
    (finally
      (println "finally: division attempt finished"))))

(defn text-length [text]
  (try
    (.length text)
    (catch NullPointerException e
      (println "NullPointerException caught: text is nil")
      0)
    (finally
      (println "finally: length check finished"))))

(defn validate-age [age]
  (if (neg? age)
    (throw (ex-info "Invalid age" {:age age :reason "Age cannot be negative"}))
    age))

(defn check-age [age]
  (try
    (let [valid (validate-age age)]
      (println "Valid age:" valid)
      valid)
    (catch clojure.lang.ExceptionInfo e
      (println "ExceptionInfo caught")
      (println "Message:" (ex-message e))
      (println "Data   :" (ex-data e))
      (println "Recovering with default age 18")
      18)
    (finally
      (println "finally: age check finished"))))

(println "===== 1. ArithmeticException =====")
(println "Result:" (safe-divide 10 2))
(println "Result:" (safe-divide 10 0))

(println "\n===== 2. NullPointerException =====")
(println "Result:" (text-length "Clojure"))
(println "Result:" (text-length nil))

(println "\n===== 3. Custom exception with ex-info =====")
(println "Final age:" (check-age 25))
(println "Final age:" (check-age -5))

(println "\n===== 4. Graceful recovery using ex-message and ex-data =====")
(try
  (throw (ex-info "Database connection failed" {:host "localhost" :port 5432}))
  (catch clojure.lang.ExceptionInfo e
    (println "Error message:" (ex-message e))
    (println "Host:" (:host (ex-data e)))
    (println "Port:" (:port (ex-data e)))
    (println "Program continues normally"))
  (finally
    (println "finally: cleanup done")))

(println "\nProgram finished")