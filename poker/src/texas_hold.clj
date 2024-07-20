(ns texas-hold)

(def player ["Mine" "Player1" "Player2" "Player3" "Player4" "Player5" "Player6" "Player7" "Player8"])
(def seat ["SB" "BB" "UTG" "UTG+1" "UTG+2" "LJ" "HJ" "CO" "BTN"])
(def suit ["♣" "♠" "♦" "♥"])
(def rank ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"])
(defn get-deck [] (shuffle (vec (for [a suit b rank] (str a b)))))
(defn deal-hands [shuffle-deck ro-seat]
  (interleave
    (apply concat (repeat 2 (partition 2 (interleave ro-seat player))))
    (partition 2 (take (* 2 (count player)) shuffle-deck))))
(defn deal-public [shuffle-deck] (take 5 (drop (* 2 (count player)) shuffle-deck)))
(defn start []
  (let [deck (get-deck) hands (deal-hands deck seat) public (deal-public deck)]
    (println "Mine hands is " (nth hands (+ 1 (* 2 (.indexOf player "Mine")))))
    (println "Public " public)))


(defn handle-input []
  (println "Please enter something:")
  (let [input (read-line)]
    (println "You entered:" input)))