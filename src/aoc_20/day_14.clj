(ns aoc-20.day-14
  (:require [aoc-20.util :as util]))

(defn read-lines [filename]
  (util/read-lines filename))

;; -- Part 1 --

(defn pad-zeros [s]
  (let [num (- 36 (count s))
        pad (apply str (repeat num "0"))]
    (format "%s%s" pad s)))

(defn mask-value [mask value]
  (let [padded-value (-> (Integer/parseInt value)
                         (Integer/toBinaryString)
                         (pad-zeros))
        pairs (->> (interleave mask padded-value)
                   (map str)
                   (partition 2))]
    (apply str (map (fn [[bit-mask bit]]
                      (if (= "X" bit-mask)
                        bit
                        bit-mask)) pairs))))

(defn process-instruction [{:keys [mask] :as state} instruction]
  (if-let [[_ new-mask] (re-matches #"^mask = (\w+)$" instruction)]
    (assoc state :mask new-mask)
    (let [[_ slot value] (re-matches #"^mem\[(\d+)\] = (\d+)$" instruction)]
      (assoc-in state [:memory slot] (mask-value mask value)))))

(defn add-binary-string [l bs]
  (+ l
     (Long/parseLong bs 2)))

(defn part-1 [input]
  (->> (reduce process-instruction {} input)
       (:memory)
       (vals)
       (reduce add-binary-string 0)))

; (part-1 (read-lines "day14-example-input.txt"))
; (part-1 (read-lines "day14-input.txt"))