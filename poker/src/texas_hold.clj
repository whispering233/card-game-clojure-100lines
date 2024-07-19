(ns texas-hold)

(defn decompose [coli] (map-indexed (fn [index value] (list index value)) coli))
(def poker (vec (for [suit (decompose (list "♣" "♠" "♦" "♥"))
                      number (decompose (list "A" "2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K"))]
                  [(first suit) (first number) (str (second suit) (second number))])))
(defn shuffle-poker [poker]
  (if (empty? poker)
    []
    (let [idx (rand-int (count poker))]
      (concat [(nth poker idx)]
              (shuffle-poker (concat (subvec (vec poker) 0 idx)
                                     (subvec (vec poker) (inc idx))))))))
(defn get-poker [] (shuffle-poker poker))


