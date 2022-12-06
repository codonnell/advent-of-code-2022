(ns aoc2022.day4
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-input [f]
  (->> f io/resource slurp str/split-lines
    (map (fn [s] (rest (re-find #"(\d+)-(\d+),(\d+)-(\d+)" s))))
    (map (fn [xs] (partition-all 2 (map parse-long xs))))))

(defn fully-contains? [[a b] [x y]]
  (and (<= a x) (>= b y)))

(defn solve-a [f]
  (->> f read-input
    (filter (fn [[pair1 pair2]] (or (fully-contains? pair1 pair2)
                                (fully-contains? pair2 pair1))))
    count))

(defn overlaps? [[a b] [x y]]
  (or (<= a x b) (<= a y b)
    (<= x a y) (<= x b y)))

(defn solve-b [f]
  (->> f read-input
    (filter (fn [[pair1 pair2]] (overlaps? pair1 pair2)))
    count))

(comment
  (re-find #"(\d+)-(\d+),(\d+)-(\d+)" "2-3,6-8")
  (solve-a "day4test.txt")
  (solve-a "day4input1.txt")
  (solve-b "day4test.txt")
  (solve-b "day4input1.txt")
  )
