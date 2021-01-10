(ns aoc-20.day-18
  (:require [aoc-20.util :as util]
            [clojure.string :as s]))

(defn read-lines [filename]
  (util/read-lines filename))

;; -- Part 1 --

(defn calculate
  "Returns the value of all the operations in a string instruction
   line. Works from left to right assuming no parenthesis."
  [operations]
  (loop [operations (s/split operations #" ")]
    (let [[v1 op v2] (subvec operations 0 3)
          result ((resolve (symbol op)) (util/to-long v1) (util/to-long v2))
          remaining-ops (subvec operations 3)]
      (if (empty? remaining-ops)
        result
        (recur (into [(str result)] remaining-ops))))))

(defn escape-char [c]
  (let [escape-chars #{"(" ")" "*" "+"}]
    (if (escape-chars c)
      (str "\\" c)
      c)))

(defn escape-specials
  "Look for special characters in the string and escape them."
  [operation]
  (->> (s/split operation #"")
       (map str)
       (map escape-char)
       (apply str)))

(defn process-parens 
  "Processes parenthesis from a list of operations 1 at a time. Calls
   calculate to find the value inside the parenthesis and adds it
   to the list of instructions."
  [operations]
  (loop [operations operations]
    (if (re-find #"\(" operations)
      (let [[_ operation] (re-find #"(\([^()]*\))" operations)
            [_ no-parens] (re-find #"\((.*)\)" operation)
            result        (str (calculate no-parens))]
        (recur (s/replace operations
                          (re-pattern (escape-specials operation))
                          result)))
      operations)))

(defn process-line [line]
  (-> (process-parens line)
      (calculate)))

(defn part-1 [input]
  (reduce + (map process-line input)))

; (part-1 (read-lines "day18-example-input.txt"))
; (part-1 (read-lines "day18-input.txt"))