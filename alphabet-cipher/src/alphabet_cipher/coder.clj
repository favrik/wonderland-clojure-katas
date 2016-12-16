(ns alphabet-cipher.coder)

(def alpha (map char (range 97 123)))

(defn pad-len [len to-pad-len] (inc (quot to-pad-len len)))

(defn padded
  [password message-count]
  (let [pass-len (count password)
        length (pad-len pass-len message-count)]
    (subs (apply str (repeat length password)) 0 message-count)))

(defn mapa [string] (map #(.indexOf alpha %) string))

(defn encode-word [word] (mapa (seq word)))

(defn coder [thing]
  (->> thing
       (map #(mod % 26))
       (map #(nth alpha %))
       (apply str)))

(defn encode [keyword message]
  (let [padded (padded keyword (count message))]
    (->> (map + (encode-word padded) (encode-word message))
         coder)))

(defn decode [keyword message]
   (let [padded (padded keyword (count message))]
    (->> (map - (encode-word padded) (encode-word message))
         (map #(- % 26))
         (map #(Math/abs %))
         coder)))

(defn find-repeat [string]
  (loop [n 2
        words (partition-all n string)]
    (if (or (>= n (count string)) (= (nth words 0) (nth words 1)))
      (apply str (nth words 0))
      (recur (inc n) (partition-all (inc n) string)))))

(defn decipher [cipher message]
  (->> (decode message cipher)
       find-repeat))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]

  ;;(println (padded "zomg" 10))
  ;;(println alpha)
  ;;(println (mapa (seq "zomg")))
  ;;(println (encode "scones" "meetmebythetree"))
  (println (decipher "egsgqwtahuiljgs" "meetmebythetree"))
  (println (find-repeat "abcabcabc")))
