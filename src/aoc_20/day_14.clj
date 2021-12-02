(ns aoc-20.day-14
  (:require [aoc-utils.core :as util]
            [clojure.string :as s]))

(defn read-lines [filename]
  (util/read-lines filename))

;; -- Part 1 --

(defn pad-zeros [s]
  (let [num (- 36 (count s))
        pad (apply str (repeat num "0"))]
    (format "%s%s" pad s)))

(defn pad-value [v]
  (-> (Integer/parseInt v)
      (Integer/toBinaryString)
      (pad-zeros)))

(defn mask-value [mask value]
  (let [padded-value (pad-value value)
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

;; -- Part 2 --

(defn has-x? [address]
  (some? (re-find #"X" address)))

(defn convert-x [address]
  (flatten
   (for [new-address [(s/replace-first address #"X" "0")
                      (s/replace-first address #"X" "1")]]
     (if (has-x? new-address)
       (convert-x new-address)
       new-address))))

(defn get-addresses [address]
  (if (has-x? address)
    (convert-x address)
    [address]))

;; - If the bitmask bit is 0, the corresponding memory address bit is
;;   unchanged.
;; - If the bitmask bit is 1, the corresponding memory address bit is
;;   overwritten with 1.
;; - If the bitmask bit is X, the corresponding memory address bit is
;;   floating. (will replace with both 0 and 1)
(defn mask-address [mask address]
  (let [padded-value (pad-value address)
        pairs (->> (interleave mask padded-value)
                   (map str)
                   (partition 2))]
    (apply str (map (fn [[bit-mask bit]]
                      (if (or (= "X" bit-mask)
                              (= "1" bit-mask))
                        bit-mask
                        bit)) pairs))))

(defn p2-process-instruction [{:keys [mask] :as state} instruction]
  (if-let [[_ new-mask] (re-matches #"^mask = (\w+)$" instruction)]
    (assoc state :mask new-mask)
    (let [[_ slot value] (re-matches #"^mem\[(\d+)\] = (\d+)$" instruction)
          masked-address (mask-address mask slot)
          padded-value (pad-value value)]
      (reduce (fn [state address]
                (assoc-in state [:memory address] padded-value))
              state
              (get-addresses masked-address)))))

(defn part-2 [input]
  (->> (reduce p2-process-instruction {} input)
       (:memory)
       (vals)
       (reduce add-binary-string 0)))

; (part-2 (read-lines "day14-example-input2.txt"))
; (part-2 (read-lines "day14-input.txt"))