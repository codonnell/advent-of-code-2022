(ns aoc2022.day6
  (:require [clojure.java.io :as io]))

(defn read-input [f]
  (-> f io/resource slurp))

(defn solve-a [f]
  (->> f read-input
    (partition-all 4 1)
    (map-indexed vector)
    (filter (comp #(apply distinct? %) second))
    ffirst
    (+ 4)))

(defn solve-b [f]
  (->> f read-input
    (partition-all 14 1)
    (map-indexed vector)
    (filter (comp #(apply distinct? %) second))
    ffirst
    (+ 14)))

(comment
  (solve-a "day6test.txt")
  (solve-a "day6input.txt")
  (solve-b "day6test.txt")
  (solve-b "day6input.txt")
  )
