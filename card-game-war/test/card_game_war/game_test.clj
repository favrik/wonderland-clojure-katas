(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (= 1 (play-round [:spade 5] [:spade 2]))))
  (testing "queens are higher rank than jacks"
    (is (= 1 (play-round [:spade :queen] [:spade :jack]))))
  (testing "kings are higher rank than queens"
    (is (= 1 (play-round [:spade :king] [:spade :queen]))))
  (testing "aces are higher rank than kings"
    (is (= 1 (play-round [:spade :ace] [:spade :king]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (= 1 (play-round [:club :ace] [:spade :ace]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (= 1 (play-round [:diamond :ace] [:club :ace]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (= 1 (play-round [:heart :ace] [:diamond :ace])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"
    (is (= 1 (play-game [[:heart :ace]] [[:spade :jack] [:club :jack]])))
    (is (= -1 (play-game [[:spade :jack]] [[:club :jack]])))))

