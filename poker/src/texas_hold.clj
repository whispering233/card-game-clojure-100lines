(ns texas-hold)

(def player ["Mine" "Player1" "Player2" "Player3" "Player4" "Player5" "Player6" "Player7" "Player8"])
(def seat ["SB" "BB" "UTG" "UTG+1" "UTG+2" "LJ" "HJ" "CO" "BTN"])
(def suit ["♣" "♠" "♦" "♥"])
(def rank ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"])
(defn get-deck [] (shuffle (vec (for [a suit b rank] (str a b)))))
(defn deal-hands [shuffle-deck]
  (apply hash-map (interleave seat
                              (partition 2 (take (* 2 (count player)) shuffle-deck)))))
(defn deal-public [shuffle-deck] (take 5 (drop (* 2 (count player)) shuffle-deck)))
(defn bind-player [ro-seat] (apply hash-map (interleave ro-seat player)))
(defn rotate-seat [seat] (concat (take-last 1 seat) (drop-last 1 seat)))
(defn start [deck ro-seat]
  (let [seat-player (bind-player ro-seat)
        hands (deal-hands deck)
        public (deal-public deck)]
    (doseq [speaker seat] (println speaker (seat-player speaker) (hands speaker)))
    (println public))
  (let [continue (read-line)]
    (if (and continue (not= "exit" continue))
      (start (get-deck) (rotate-seat ro-seat))
      (println "EXIT GAME"))))