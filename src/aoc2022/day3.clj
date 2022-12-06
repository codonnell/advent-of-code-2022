(ns aoc2022.day3
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(defn split-rucksack [s]
  (let [n (count s)]
    (split-at (quot n 2) s)))

(defn read-input [f]
  (->> f io/resource slurp str/split-lines ))

(defn priority [x]
  (if (> (int x) (int \a))
    (inc (- (int x) (int \a)))
    (+ 27 (- (int x) (int \A)))))

(defn solve-a [f]
  (->> f read-input
    (map split-rucksack)
    (map (fn [[a b]] (first (set/intersection (set a) (set b)))))
    (map priority)
    (reduce + 0)))

(defn solve-b [f]
  (->> f read-input
    (map seq)
    (partition-all 3)
    (map (fn [[a b c]] (first (set/intersection (set a) (set b) (set c)))))
    (map priority)
    (reduce + 0)))

(comment
  (split-at 10 (range 20))
  (priority \b)
  (priority \B)
  (- (int \B) (int \a))
  (solve-a "day3test.txt")
  (solve-a "day3input1.txt")
  (solve-b "day3test.txt")
  (solve-b "day3input1.txt")
  )
