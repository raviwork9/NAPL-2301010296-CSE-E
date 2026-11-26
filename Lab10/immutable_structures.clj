(println "=== LIST ===")
(def my-list (list 1 2 3))
(println "Original list :" my-list)
(println "first         :" (first my-list))
(println "rest          :" (rest my-list))

(def new-list (conj my-list 0))
(println "After conj 0  :" new-list)
(println "Original again:" my-list)
(println)

(println "=== VECTOR ===")
(def my-vec [10 20 30])
(println "Original vector:" my-vec)

(def new-vec (conj my-vec 40))
(println "After conj 40  :" new-vec)
(println "get index 1    :" (get my-vec 1))
(println "count original :" (count my-vec))
(println "count new      :" (count new-vec))
(println "Original again :" my-vec)
(println)

(println "=== MAP ===")
(def my-map {:name "Alice" :age 25})
(println "Original map  :" my-map)

(def map-with-city (assoc my-map :city "Delhi"))
(println "After assoc   :" map-with-city)

(def map-no-age (dissoc my-map :age))
(println "After dissoc  :" map-no-age)

(println "get :name     :" (get my-map :name))
(println "get :salary   :" (get my-map :salary "not found"))
(println "Original again:" my-map)
(println)

(println "=== SET ===")
(def my-set #{1 2 3})
(println "Original set  :" my-set)

(def set-with-4 (conj my-set 4))
(println "After conj 4  :" set-with-4)

(def set-no-2 (disj my-set 2))
(println "After disj 2  :" set-no-2)

(println "contains? 3   :" (contains? my-set 3))
(println "contains? 9   :" (contains? my-set 9))
(println "Original again:" my-set)