(ns texas-hold)

(defn decompose [coli] (map-indexed (fn [index value] (list index value)) coli))
(def poker (for [suit (decompose (list "♣" "♠" "♦" "♥"))
                 number (decompose (list "A" "1" "2" "3" "4" "5" "6" "7" "8" "9" "10" "J" "Q" "K"))]
             ((fn [[a b] [c d]] (list a c (str b d))) suit number)))