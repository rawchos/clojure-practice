(ns aoc-utils.core
  (:require [clojure.java.io :as io]))

(defn read-lines 
  "Reads in an input file from src/aoc_[year]/inputs/ and creates a sequence."
  ([input-file]
   (read-lines "20" input-file))
  ([aoc-year input-file]
   (with-open [rdr (-> (str "aoc_" aoc-year "/" input-file)
                       io/resource
                       io/reader)]
     (doall (line-seq rdr)))))

(defn read-str
  "This function reads the entire input into a string that we can
   then parse through, rather than reading each line."
  ([filename]
   (read-str "20" filename))
  ([aoc-year filename]
   (-> (str "aoc_" aoc-year "/" filename)
       io/resource
       slurp)))

(defn to-int 
  "Convenience method for coercing a string to an int."
  [s]
  (Integer/parseInt s))

(defn to-long 
  "Convenience method for coercing a string to a long."
  [s]
  (Long/parseLong s))