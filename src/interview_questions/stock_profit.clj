(ns interview-questions.stock-profit)

;; Question: For an array of stock profits and a target, find all
;; unique pairs of stock targets that add up to the target amount.
;; Return the count of unique pairs.

(defn at-least-2? [profits num]
  (-> (filter #(= % num) profits)
      (count)
      (> 1)))

;; Initial solution. Can be refined.
(defn stock-profit [stock-profits target]
  (-> (let [profit-set (set stock-profits)
            target target]
        (loop [profits stock-profits
               matches []]
          (if-let [profit (first profits)]
            (if-let [match (profit-set (- target profit))]
              (recur (rest profits) (conj matches (if (= profit match)
                                                    (when (at-least-2? stock-profits profit)
                                                      #{profit})
                                                    #{profit match})))
              (recur (rest profits) matches))
            matches)))
      (set)
      (count)))
