(ns texas-hold)

(def player ["Mine" "Player1" "Player2" "Player3" "Player4" "Player5" "Player6" "Player7" "Player8"])
(def seat ["SB" "BB" "UTG" "UTG+1" "UTG+2" "LJ" "HJ" "CO" "BTN"])
(def suit ["♣" "♠" "♦" "♥"])
(def rank ["2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K" "A"])
(defn get-deck [] (shuffle (vec (for [a suit b rank] (str a b)))))
(defn rotate-seat [seat] (concat (take-last 1 seat) (drop-last 1 seat)))
(defn start [deck ro-seat]
  (let [seat-player (apply hash-map (interleave ro-seat player))
        hands (apply hash-map (interleave seat (partition 2 (take (* 2 (count player)) deck))))
        public (take 5 (drop (* 2 (count player)) deck))]
    (doseq [speaker seat] (println speaker (seat-player speaker) (hands speaker)))
    (println public))
  (let [continue (read-line)]
    (if (and continue (not= "exit" continue))
      (start (get-deck) (rotate-seat ro-seat))
      (println "EXIT GAME"))))