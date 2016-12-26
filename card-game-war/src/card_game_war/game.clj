(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn rank-value [card]
  (.indexOf ranks (nth card 1)))

(defn suit-value [card]
  (.indexOf suits (nth card 0)))

(defn play-round [player1-card player2-card]
  (if (= (rank-value player1-card) (rank-value player2-card))
    (compare (suit-value player1-card) (suit-value player2-card))
    (compare (rank-value player1-card) (rank-value player2-card))))

(defn real-play [[p1cards p2cards]]
  (let [p1 (first p1cards) p2 (first p2cards)]
    (if (= 1 (play-round p1 p2))
      [(conj (drop 1 p1cards) p1 p2) (drop 1 p2cards)]
      [(drop 1 p1cards) (conj (drop 1 p2cards) p1 p2)])))

(defn play-game [player1-cards player2-cards]
  (loop [leset [player1-cards player2-cards]]
    (cond
      (empty? (first leset)) -1
      (empty? (second leset)) 1
      :else (recur (real-play leset)))))
