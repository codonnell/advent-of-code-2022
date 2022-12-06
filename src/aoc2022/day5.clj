(ns aoc2022.day5
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))

(defn stack-items [lines stack]
  (let [idx (str/index-of (last lines) stack)]
    (->> (butlast lines)
      reverse
      (map #(subs % idx (inc idx)))
      (into [] (remove str/blank?)))))

(defn read-input [f]
  (let [[stack instructions] (->> f io/resource slurp str/split-lines (split-with #(re-find #"\s+" %)))
        stacks (rest (str/split (last stack) #"\s+"))]
    {:stacks (zipmap stacks (map #(stack-items stack %) stacks))
     :instructions (->> instructions
                     rest
                     (map (fn [s] (re-find #"move (\d+) from (\d+) to (\d+)" s)))
                     (map (fn [[_ n from to]] {:n (parse-long n) :from from :to to})))}))

(defn solve-a [f]
  (let [{:keys [stacks instructions]} (read-input f)]
    (->> instructions
      (mapcat (fn [{:keys [n] :as m}] (repeat n (dissoc m :n))))
      (reduce (fn [stacks {:keys [from to]}]
                (let [x (peek (get stacks from))]
                  (-> stacks
                    (update from pop)
                    (update to conj x))))
        stacks)
      (into {} (map (fn [[k v]] [k (peek v)])))
      (sort-by key)
      (map val)
      (apply str))))

(defn solve-b [f]
  (let [{:keys [stacks instructions]} (read-input f)]
    (->> instructions
      (reduce (fn [stacks {:keys [n from to]}]
                (let [stack (get stacks from)
                      stack-size (count stack)
                      crates (subvec stack (- stack-size n) stack-size)]
                  (-> stacks
                    (update from subvec 0 (- stack-size n))
                    (update to into crates))))
        stacks)
      (into {} (map (fn [[k v]] [k (peek v)])))
      (sort-by key)
      (map val)
      (apply str))))

(comment
  (read-input "day5test.txt")
  (solve-a "day5test.txt")
  (solve-a "day5input1.txt")
  (solve-b "day5test.txt")
  (solve-b "day5input1.txt")
  )
