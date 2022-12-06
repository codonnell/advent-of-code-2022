(ns aoc2022.day2
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clojure.set :as set]))

(def beats {:rock :scissors
            :scissors :paper
            :paper :rock})

(def loses (set/map-invert beats))

(def opponent-plays {"A" :rock
                     "B" :paper
                     "C" :scissors})

(def i-play {"X" :rock
             "Y" :paper
             "Z" :scissors})

(def result {"X" :loss
             "Y" :draw
             "Z" :win})

(def score {:rock 1
            :paper 2
            :scissors 3})

(defn read-input [f]
  (->> f io/resource slurp str/split-lines
    (map #(str/split % #"\s"))))

(defn calculate-score [{:keys [me res]}]
  (+ (score me)
    (case res
      :win 6
      :loss 0
      :draw 3)))

(defn solve-a [f]
  (->> f read-input
    (map (fn [[opp me]] {:opp (opponent-plays opp)
                         :me (i-play me)}))
    (map (fn [{:keys [opp me] :as m}]
           (assoc m :res (cond (= opp (beats me)) :win
                               (= me (beats opp)) :loss
                               :else :draw))))
    (map calculate-score)
    (reduce + 0)))

(defn solve-b [f]
  (->> f read-input
    (map (fn [[opp res]]
           {:opp (opponent-plays opp)
            :res (result res)}))
    (map (fn [{:keys [opp res] :as m}]
           (assoc m :me (case res
                          :win (loses opp)
                          :loss (beats opp)
                          :draw opp))))
    (map calculate-score)
    (reduce + 0)))

(comment
  (read-input "day2test.txt")
  (solve-a "day2test.txt")
  (solve-a "day2input1.txt")
  (solve-b "day2test.txt")
  (solve-b "day2input1.txt")
  )
