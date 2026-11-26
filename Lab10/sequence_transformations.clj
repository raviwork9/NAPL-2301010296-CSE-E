(println "=== COLLECTION 1: numbers ===")
(def nums [1 2 3 4 5 6 7 8 9 10])
(println "Original      :" nums)

(println "=== (1) MAP ===")
(println "Squares       :" (map #(* % %) nums))
(println "Plus ten      :" (map #(+ % 10) nums))

(println "=== (2) FILTER ===")
(println "Evens         :" (filter even? nums))
(println "Greater than 5:" (filter #(> % 5) nums))

(println "=== (3) REDUCE ===")
(println "Sum           :" (reduce + nums))
(println "Maximum       :" (reduce max nums))

(println "=== (4) TAKE and DROP ===")
(println "take 3        :" (take 3 nums))
(println "drop 3        :" (drop 3 nums))
(println "drop 2, take 4:" (take 4 (drop 2 nums)))

(println "=== THREADING ->> ===")
(println "Squares of evens, summed:"
         (->> nums
              (filter even?)
              (map #(* % %))
              (reduce +)))
(println "Drop 2, take 5, double  :"
         (->> nums
              (drop 2)
              (take 5)
              (map #(* % 2))))

(println)
(println "=== COLLECTION 2: words ===")
(def words ["clojure" "is" "a" "functional" "language" "on" "the" "jvm"])
(println "Original      :" words)

(println "=== (1) MAP ===")
(println "Lengths       :" (map count words))
(println "Uppercase     :" (map clojure.string/upper-case words))

(println "=== (2) FILTER ===")
(println "Longer than 3 :" (filter #(> (count %) 3) words))

(println "=== (3) REDUCE ===")
(println "Total letters :" (reduce + (map count words)))
(println "Longest word  :" (reduce #(if (> (count %2) (count %1)) %2 %1) words))

(println "=== (4) TAKE and DROP ===")
(println "take 3        :" (take 3 words))
(println "drop 5        :" (drop 5 words))

(println "=== THREADING ->> ===")
(println "Long words, uppercased, first 3:"
         (->> words
              (filter #(> (count %) 3))
              (map clojure.string/upper-case)
              (take 3)))

(println "=== THREADING -> ===")
(println "Thread-first on a map:"
         (-> {:a 1 :b 2}
             (assoc :c 3)
             (dissoc :a)
             (update :b + 10)))