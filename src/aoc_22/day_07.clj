(ns aoc-22.day-07
  (:require [aoc-utils.core :as util]
            [clojure.string :as str]))

(defn day7-input []
  (util/read-lines "22" "day07.txt"))

(defn cd? [command]
  (boolean (re-matches #"\$ cd .*" command)))

(defn ls? [command]
  (boolean (re-matches #"\$ ls" command)))

(defn dir? [command]
  (boolean (re-matches #"dir .*" command)))

(defn change-dir [tree-map to-dir]
  (case to-dir
    "/" (assoc tree-map :pwd ["/"])
    ".." (assoc tree-map
                :pwd
                (-> (drop-last (:pwd tree-map))
                    (#(if (seq %) % ["/"]))))
    (update tree-map :pwd concat [to-dir])))

(defn add-dir [{:keys [pwd] :as tree-map} dir]
  (assoc-in tree-map
            (concat [:tree] pwd [dir])
            {:files []}))

(defn add-file [{:keys [pwd] :as tree-map} [file-size file-name]]
  (update-in tree-map
             (concat [:tree] pwd [:files])
             conj
             {:file-size (Long/parseLong file-size)
              :file-name file-name}))

(defn build-tree [tree-map input]
  (cond
    (cd? input)  (change-dir tree-map (last (str/split input #" ")))
    (ls? input)  tree-map
    (dir? input) (add-dir tree-map (last (str/split input #" ")))
    :else        (add-file tree-map (str/split input #" "))))

(defn list-dirs [[dir-name contents]]
  (let [sum-files  (->> (map :file-size (:files contents))
                        (reduce +))
        sub-dirs (->> (seq contents)
                      (remove #(= :files (first %)))
                      (map list-dirs))
        sub-dir-size (->> sub-dirs
                          (map :dir-size)
                          (reduce +))
        this-dir {:dir dir-name :dir-size (+ sum-files sub-dir-size)}]
    (merge this-dir
           {:sub-dirs (-> (map :sub-dirs sub-dirs)
                           flatten
                           (conj this-dir))})))

(defn smallest-delete [{:keys [dir dir-size sub-dirs]}]
  (let [currently-avail (- 70000000 dir-size)]
    (->> (remove #(= dir (:dir %)) sub-dirs)
         (filter #(<= 30000000 (+ currently-avail
                                  (:dir-size %))))
         (sort-by :dir-size)
         first)))

(comment
  ; part 1 => 1989474
  (->> (day7-input)
       (reduce build-tree {:pwd ["/"] :tree {}})
       :tree
       first
       list-dirs
       :sub-dirs
       (filter #(<= (:dir-size %) 100000))
       (map :dir-size)
       (reduce +))
  
  ; part 2 => 1111607
  (->> (day7-input)
       (reduce build-tree {:pwd ["/"] :tree {}})
       :tree
       first
       list-dirs
       smallest-delete
       :dir-size)
  )