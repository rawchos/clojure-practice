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


; Question 1
(def open-brackets #{"(" "{" "["})
(def matching-bracket {")" "("
                       "}" "{"
                       "]" "["})

(defn balanced? [s]
  (loop [chars (map str s)
         brackets []]
    (if-let [current-char (first chars)]
      (if (contains? open-brackets current-char)
        (recur (rest chars) (conj brackets current-char))
        (if (= (get matching-bracket current-char) (last brackets))
          (recur (rest chars) (into [] (drop-last brackets)))
          "NO"))
      (if (empty? brackets)
        "YES"
        "NO"))))

(defn matchingBraces [braces]
  (map balanced? braces))

; Question 2
(defn get-host [[_ host]]
  {:host host})

(defn log-stats [filename hosts]
  (doseq [[host lines] hosts]
    (spit filename (format "%s %d" host (count lines)) :append true)))

(defn process-file [filename]
  (->> (slurp filename)
       (re-seq #"(\S*) - .*\n")
       (map get-host)
       (group-by :host)
       (log-stats (format "records_%s" filename))))

(process-file "test_log_file.txt")

;; Updated with data from hackerrank:
(comment 
  #_(def filename (read-line))

  #_(defn get-host [[_ host]]
      {:host host})

  #_(defn log-stats [filename hosts]
      (doseq [[host lines] hosts]
        (spit filename (format "%s %d" host (count lines)) :append true)))

  #_(defn process-file []
      (let [hosts-file (filename)]
        (->> (slurp hosts-file)
             (re-seq #"(\S*) - .*\n")
             (map get-host)
             (group-by :host)
             (log-stats (format "records_%s" hosts-file)))))

  )

; Question 3:
{"0" #{"0" "1"}
 "1" #{"0" "1" "2"}
 "2" #{"1" "2"}
 "3" #{"3"}}

; ["1100" "1110" "0110" "0001"] => 2
(defn get-relationships [related]
  (let [persons (count related)]
    (for [x (range 0 persons)
          y (range 0 persons)
          :when (= \1 (-> (nth related x)
                          (nth y)))]
      [x y])))

(defn add-relationship [relationships r]
  (if relationships
    (conj relationships r)
    #{r}))

(defn build-relationship-map [relationships]
  (reduce (fn [m [x y]] (update m (str x) add-relationship y))
          {}
          relationships))

(defn find-index-of-group [groups this-group]
  (->> (map-indexed (fn [idx group]
                      (when (seq (clojure.set/intersection group this-group))
                        idx)) groups)
      (remove nil?)
      first))

(defn add-to-group [groups this-group]
  (if-let [index (find-index-of-group groups this-group)]
    (update-in groups
               [index]
               clojure.set/union this-group)
    (conj groups this-group)))

(defn build-groups [m]
  (loop [remaining-relationships (vals m)
         existing-groups []]
    (if-let [this-group (first remaining-relationships)]
      (recur (rest remaining-relationships) (add-to-group existing-groups this-group))
      existing-groups)))

(defn countGroups [related]
  (-> (get-relationships related)
      build-relationship-map
      build-groups
      count))


(countGroups ["1100" "1110" "0110" "0001"])
