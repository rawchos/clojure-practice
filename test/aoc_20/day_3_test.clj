(ns aoc-20.day-3-test
  (:require [clojure.test :refer [deftest is testing]]
            [clojure.string :as s]
            [aoc-20.day-3 :as d3]))

(deftest tree?-test
       (testing "should be true if the thing is '#'"
             (is (true? (d3/tree? "#"))))
       (testing "should be false if anything else"
             (is (false? (d3/tree? ".")))
             (is (false? (d3/tree? nil)))))

(deftest next-column-test
       (testing "should wrap around the column width"
             (is (= (d3/next-column 1 3 4) 0))
             (is (= (d3/next-column 2 3 4) 1))
             (is (= (d3/next-column 0 3 4) 3))))

(deftest part-1-test
       (let [input ["..##......."
                    "#...#...#.."
                    ".#....#..#."
                    "..#.#...#.#"
                    ".#...##..#."
                    "..#.##....."
                    ".#.#.#....#"
                    ".#........#"
                    "#.##...#..."
                    "#...##....#"
                    ".#..#...#.#"]
             grid (for [row input]
                    (s/split row #""))]
         (testing "should correctly count the number of trees hit"
               (is (= (d3/part-1 grid) 7)))))

(deftest count-trees-test
       (let [input ["..##......."
                    "#...#...#.."
                    ".#....#..#."
                    "..#.#...#.#"
                    ".#...##..#."
                    "..#.##....."
                    ".#.#.#....#"
                    ".#........#"
                    "#.##...#..."
                    "#...##....#"
                    ".#..#...#.#"]
             grid (for [row input]
                    (s/split row #""))
             slope-patterns [{:slope-right 1 :slope-down 1}
                             {:slope-right 3 :slope-down 1}
                             {:slope-right 5 :slope-down 1}
                             {:slope-right 7 :slope-down 1}
                             {:slope-right 1 :slope-down 2}]]
         (testing "should count the number of trees correctly"
               (is (= (d3/count-trees grid (nth slope-patterns 0)) 2))
               (is (= (d3/count-trees grid (nth slope-patterns 1)) 7))
               (is (= (d3/count-trees grid (nth slope-patterns 2)) 3))
               (is (= (d3/count-trees grid (nth slope-patterns 3)) 4))
               (is (= (d3/count-trees grid (nth slope-patterns 4)) 2)))))

(deftest part-2-test
       (let [input ["..##......."
                    "#...#...#.."
                    ".#....#..#."
                    "..#.#...#.#"
                    ".#...##..#."
                    "..#.##....."
                    ".#.#.#....#"
                    ".#........#"
                    "#.##...#..."
                    "#...##....#"
                    ".#..#...#.#"]
             grid (for [row input]
                    (s/split row #""))
             slope-patterns [{:slope-right 1 :slope-down 1}
                             {:slope-right 3 :slope-down 1}
                             {:slope-right 5 :slope-down 1}
                             {:slope-right 7 :slope-down 1}
                             {:slope-right 1 :slope-down 2}]]
         (testing "should correctly count the number of trees hit"
               (is (= (d3/part-2 grid slope-patterns) 336)))))