(ns aoc-20.util
  (:require [clojure.java.io :as io]))

(defn read-lines 
  "Reads in an input file from src/aoc_20/inputs/ and creates a sequence."
  [input-file]
  (with-open [rdr (io/reader (str "src/aoc_20/inputs/" input-file))]
    (doall (line-seq rdr))))

(defn read-str
  "This function reads the entire input into a string that we can
   then parse through, rather than reading each line."
  [filename]
  (slurp (str "src/aoc_20/inputs/" filename)))