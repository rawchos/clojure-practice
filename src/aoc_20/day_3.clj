(ns aoc-20.day-3
  (:require [clojure.string :as s]
            [aoc-20.util :as util]))

;; --- Day 3: Toboggan Trajectory ---
;; With the toboggan login problems resolved, you set off toward the
;; airport. While travel by toboggan might be easy, it's certainly not
;; safe: there's very minimal steering and the area is covered in trees.
;; You'll need to see which angles will take you near the fewest trees.
;; 
;; Due to the local geology, trees in this area only grow on exact integer
;; coordinates in a grid. You make a map (your puzzle input) of the open
;; squares (.) and trees (#) you can see. For example:
;; 
;; ..##.......
;; #...#...#..
;; .#....#..#.
;; ..#.#...#.#
;; .#...##..#.
;; ..#.##.....
;; .#.#.#....#
;; .#........#
;; #.##...#...
;; #...##....#
;; .#..#...#.#
;;
;; These aren't the only trees, though; due to something you read about
;; once involving arboreal genetics and biome stability, the same pattern
;; repeats to the right many times:
;; 
;; ..##.........##.........##.........##.........##.........##.......  --->
;; #...#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
;; .#....#..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
;; ..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
;; .#...##..#..#...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
;; ..#.##.......#.##.......#.##.......#.##.......#.##.......#.##.....  --->
;; .#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
;; .#........#.#........#.#........#.#........#.#........#.#........#
;; #.##...#...#.##...#...#.##...#...#.##...#...#.##...#...#.##...#...
;; #...##....##...##....##...##....##...##....##...##....##...##....#
;; .#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
;;
;; You start on the open square (.) in the top-left corner and need to
;; reach the bottom (below the bottom-most row on your map).
;; 
;; The toboggan can only follow a few specific slopes (you opted for a
;; cheaper model that prefers rational numbers); start by counting all the
;; trees you would encounter for the slope right 3, down 1:
;; 
;; From your starting position at the top-left, check the position that is
;; right 3 and down 1. Then, check the position that is right 3 and down 1
;; from there, and so on until you go past the bottom of the map.
;; 
;; The locations you'd check in the above example are marked here with O
;; where there was an open square and X where there was a tree:
;; 
;; ..##.........##.........##.........##.........##.........##.......  --->
;; #..O#...#..#...#...#..#...#...#..#...#...#..#...#...#..#...#...#..
;; .#....X..#..#....#..#..#....#..#..#....#..#..#....#..#..#....#..#.
;; ..#.#...#O#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#..#.#...#.#
;; .#...##..#..X...##..#..#...##..#..#...##..#..#...##..#..#...##..#.
;; ..#.##.......#.X#.......#.##.......#.##.......#.##.......#.##.....  --->
;; .#.#.#....#.#.#.#.O..#.#.#.#....#.#.#.#....#.#.#.#....#.#.#.#....#
;; .#........#.#........X.#........#.#........#.#........#.#........#
;; #.##...#...#.##...#...#.X#...#...#.##...#...#.##...#...#.##...#...
;; #...##....##...##....##...#X....##...##....##...##....##...##....#
;; .#..#...#.#.#..#...#.#.#..#...X.#.#..#...#.#.#..#...#.#.#..#...#.#  --->
;;
;; In this example, traversing the map using this slope would cause you to
;; encounter 7 trees.
;; 
;; Starting at the top-left corner of your map and following a slope of
;; right 3 and down 1, how many trees would you encounter?
(defn tree? [thing]
  (= "#" thing))

(defn next-column 
  "This function assumes a zero-based index and will wrap around once
   we pass the width of the column."
  [current adjust width]
  (let [next (+ current adjust)]
    (if (>= next width)
      (- next width)
      next)))

(defn part-1 [input]
  (->> (let [column-width (count (first input))
             slope-right  3]
         (loop [rows-left (range (count input))
                column    0
                steps     []]
           (if-let [row (first rows-left)]
             (recur (rest rows-left)
                    (next-column column slope-right column-width)
                    (if (tree? (-> (nth input row)
                                   (nth column)))
                      (conj steps "X")
                      (conj steps "O")))
             steps)))
       (filter #(= "X" %))
       (count)))

;; --- Part Two ---
;; Time to check the rest of the slopes - you need to minimize the
;; probability of a sudden arboreal stop, after all.
;;
;; Determine the number of trees you would encounter if, for each of the
;; following slopes, you start at the top-left corner and traverse the map
;; all the way to the bottom:
;; 
;; Right 1, down 1.
;; Right 3, down 1. (This is the slope you already checked.)
;; Right 5, down 1.
;; Right 7, down 1.
;; Right 1, down 2.
;; 
;; In the above example, these slopes would find 2, 7, 3, 4, and 2 tree(s)
;; respectively; multiplied together, these produce the answer 336.
;; 
;; What do you get if you multiply together the number of trees encountered
;; on each of the listed slopes?

;; TODO: Modify this. part-2 needs to take a grid and then maybe a map
;; of parameters (slopes) to loop through. Then multiply the results
;; together. Maybe rename this function to accept the parameters and have
;; part 2 call it. Figure out the row stepping (1 vs 2).
(def slope-patterns [{:slope-right 1 :slope-down 1}
                     {:slope-right 3 :slope-down 1}
                     {:slope-right 5 :slope-down 1}
                     {:slope-right 7 :slope-down 1}
                     {:slope-right 1 :slope-down 2}])

(defn count-trees [grid {:keys [slope-right slope-down]}]
  (->> (let [column-width (count (first grid))]
         (loop [rows-left (range 0 (count grid) slope-down)
                column    0
                steps     []]
           (if-let [row (first rows-left)]
             (recur (rest rows-left)
                    (next-column column slope-right column-width)
                    (if (tree? (-> (nth grid row)
                                   (nth column)))
                      (conj steps "X")
                      (conj steps "O")))
             steps)))
       (filter #(= "X" %))
       (count)))

(defn part-2 [grid slope-patterns]
  (->> (for [slope slope-patterns]
         (count-trees grid slope))
       (reduce *)))

(def day3-example-input ["..##......."
                         "#...#...#.."
                         ".#....#..#."
                         "..#.#...#.#"
                         ".#...##..#."
                         "..#.##....."
                         ".#.#.#....#"
                         ".#........#"
                         "#.##...#..."
                         "#...##....#"
                         ".#..#...#.#"])
(defn build-example-input []
  (for [row day3-example-input]
    (s/split row #"")))

; (part-2 (for [row (util/read-lines "day3-input.txt")]
;           (s/split row #"")) slope-patterns)

;; (let [grid (for [row (aoc-20.util/read-lines "day3-input.txt")]
;;              (s/split row #""))]
;;   (part-1 grid))