(ns aoc-22.day-05
  (:require [aoc-utils.core :as util]))

;; Skip the first 10 lines because it's
;; the initial stack setup
(defn read-input []
  (->> (util/read-lines "22" "day05.txt")
       (drop 10)))

(defn parse-input []
  (->> (read-input)
       (map (partial re-seq #"\d+"))
       (map (fn [[num & stacks]]
              [(Integer/parseInt num) (mapv keyword stacks)]))))

;; [T]     [D]         [L]
;; [R]     [S] [G]     [P]         [H]
;; [G]     [H] [W]     [R] [L]     [P]
;; [W]     [G] [F] [H] [S] [M]     [L]
;; [Q]     [V] [B] [J] [H] [N] [R] [N]
;; [M] [R] [R] [P] [M] [T] [H] [Q] [C]
;; [F] [F] [Z] [H] [S] [Z] [T] [D] [S]
;; [P] [H] [P] [Q] [P] [M] [P] [F] [D]
;;  1   2   3   4   5   6   7   8   9
(def initial-stack
  {:1 [:T :R :G :W :Q :M :F :P]
   :2 [:R :F :H]
   :3 [:D :S :H :G :V :R :Z :P]
   :4 [:G :W :F :B :P :H :Q]
   :5 [:H :J :M :S :P]
   :6 [:L :P :R :S :H :T :Z :M]
   :7 [:L :M :N :H :T :P]
   :8 [:R :Q :D :F]
   :9 [:H :P :L :N :C :S :D]})

(defn add-to-stack [items stack]
  (if (seq items)
    (concat items stack)
    stack))

(defn adjust-stacks [stack-map [num [from to]]]
  (let [items (take num (get stack-map from))]
    (-> stack-map
        (update from (partial drop num))
        (update to (partial add-to-stack (reverse items))))))

(defn get-firsts [stack-map]
  (for [stack [:1 :2 :3 :4 :5 :6 :7 :8 :9]]
    (first (get stack-map stack))))

(defn adjust-stacks-pt2 [stack-map [num [from to]]]
  (let [items (take num (get stack-map from))]
    (-> stack-map
        (update from (partial drop num))
        (update to (partial add-to-stack items)))))


(comment
  ; part 1 => "TPGVQPFDH"
  (->> (parse-input)
       (reduce adjust-stacks initial-stack)
       get-firsts
       (remove nil?)
       (map name)
       (apply str))
  
  ; part 2 => "DMRDFRHHH"
  (->> (parse-input)
       (reduce adjust-stacks-pt2 initial-stack)
       get-firsts
       (remove nil?)
       (map name)
       (apply str))

  )


