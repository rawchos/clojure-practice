(ns ctci.big-o)

;; TODO: Remove this, it's just for verifying everything is setup
(defn is-working? []
  true)

;; This section takes examples in the CtCI section and rewrites
;; them in clojure.

;; Space Complexity
;; This takes O(n) time and O(n) space because each call adds to the stack
;; int sum(int n) {
;;   if (n <= 0) {
;;     return 0;
;;   }
;;   return n + sum(n-1);
;; }

(defn recur-sum 
  "This is a first approach to sum using loop and recur"
  [n]
  (loop [cnt n
         total 0]
    (if (zero? cnt)
      total
      (recur (dec cnt) (+ cnt total)))))

(defn reduce-sum
  "Hopefully a little more succint version of sum"
  [n]
  (if (pos? n)
    (reduce + (range (+ n 1)))
    0))