(ns aoc2022.day1
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn read-input [f]
  (->> f io/resource slurp str/split-lines (map parse-long) (partition-by nil?) (remove #{[nil]})))

(defn solve-a [f]
  (->> f read-input (mapv #(reduce + 0 %)) (apply max)))

(defn solve-b [f]
  (->> f read-input (mapv #(reduce + 0 %)) (sort >) (take 3) (reduce + 0)))

(comment
  (def test-input (slurp (io/resource "day1test.txt")))
  (read-input "day1test.txt")
  (solve-a "day1test.txt")
  (solve-a "day1input1.txt")
  (solve-b "day1test.txt")
  (solve-b "day1input1.txt")
  )
