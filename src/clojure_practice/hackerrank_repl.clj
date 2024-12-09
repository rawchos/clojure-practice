(ns clojure-practice.hackerrank-repl)

;; This is just used as a repl for solving hackerrank problems

; Given an array of integers, calculate the ratios of its elements that
; are positive, negative, and zero. Print the decimal value of each
; fraction on a new line with  places after the decimal.

; Note: This challenge introduces precision problems. The test cases are
; scaled to six decimal places, though answers with absolute error of up
; to  are acceptable.

; Example [1 1 0 -1 -1]

; There are 5 elements, two positive, two negative and one zero. Their
; ratios are 2/5, 2/5 and 1/5. Results are printed as:

; 0.400000
; 0.400000
; 0.200000

(defn get-count [filter-fn args]
  (-> (filter filter-fn args)
      (count)))

;; TODO: First pass, clean this up.
(defn plus-minus [args]
  (let [num (count args)
        positive (get-count pos? args)
        negative (get-count neg? args)
        zeroes   (get-count #(= 0 %) args)]
    (doseq [number [positive negative zeroes]]
      (println (format "%.6f" (double (/ number num)))))))

; Given a square matrix, calculate the absolute difference between the
; sums of its diagonals.

; For example, the square matrix arr is shown below:

; 1 2 3
; 4 5 6
; 9 8 9  

; The left-to-right diagonal = 1 + 5 + 9 = 15. The right-to-left
; diagonal = 3 + 5 + 9 = 17. Their absolute difference is 15 - 17 = 2.

(defn absolute [number]
  (if (neg? number)
    (* -1 number)
    number))

(defn diagonal-difference [matrix]
  (let [size (count (first matrix))]
    (loop [left-positions  (range size)
           right-positions (reverse left-positions)
           left-diags []
           right-diags []
           rows-left matrix]
      (let [left-position (first left-positions)
            right-position (first right-positions)
            this-row (first rows-left)]
        (if (seq this-row)
          (recur (rest left-positions)
                 (rest right-positions)
                 (conj left-diags (nth this-row left-position))
                 (conj right-diags (nth this-row right-position))
                 (rest rows-left))
          (-> (- (reduce + left-diags)
                 (reduce + right-diags))
              absolute))))))

(defn staircase [height]
  (doseq [x (range 1 (+ 1 height))]
    (prn (apply str (flatten [(repeat (- height x) " ") (repeat x "#")])))))

(defn miniMaxSum [arr]
  (println (format "%d %d"
                   (reduce + (take 4 arr))
                   (reduce + (rest arr)))))

(defn birthdayCakeCandles
  "Takes an array of integers representing candle heights. Returns the
   count of the tallest candles."
  [candles])



(comment 
  (plus-minus [1 1 0 -1 -1])
  (diagonal-difference [[1 2 3]
                        [4 5 6]
                        [9 8 9]])
  (staircase 4)
  (miniMaxSum [1 2 3 4 5])
  
  )

(comment
  (require '[clojure.java.io :as io])
  (def the-thing "IyEvdXNyL2Jpbi9lbnYgcHl0aG9uMwoKJycnCktlZXAgdXMgb3V0IG9mIGdvb2dsZSBzZWFyY2ggcmVzdWx0cy4uCgokIG9kIC1kIC9kZXYvdXJhbmRvbSB8IGhlYWQKMDAwMDAwMCAgICAgNjAyMTUgICAyODc3OCAgIDI5MjI3ICAgMjg1NDggICA2MjY4NiAgIDQ1MTcxICAgIDc4MjYgICA0ODc2NgowMDAwMDIwICAgICAxNzExOCAgIDE1MjI1ICAgMTI4NTIgICAzNDc4MSAgIDMxOTU1ICAgMTkwODcgICAzOTU2MyAgIDQzNjE0CjAwMDAwNDAgICAgICA2NzEwICAgMzg1MTUgICAxNDU3MyAgIDY0MDg3ICAgMTcwMjYgICAyNTU5OCAgIDQyOTEzICAgMTQyMDkKMDAwMDA2MCAgICAgMTA3MjMgICAzMTMwNyAgIDE5MDcxICAgMTQ3OTggICAgMjQ2MiAgIDQ2MjUzICAgMzU2MjYgICAzMjQzNgowMDAwMTAwICAgICAgMTczOSAgIDI3NzEyICAgIDU2NjcgICAxMjIxMiAgIDQ3MDc3ICAgNDE3MjIgICA1NDQ1MiAgIDM4NDYxCjAwMDAxMjAgICAgICA0ODE2ICAgMTUwMTQgICAyODYyMyAgIDEwOTI4ICAgNTQwMjggICA2NDUyMyAgIDU0NjMyICAgNTQxODcKMDAwMDE0MCAgICAgNjE2MzEgICA1NDQ5OSAgIDE4MzA3ICAgIDU1MTQgICA1MDc0MyAgIDUwNTkxICAgMjUxNzIgICA1NDAxOAowMDAwMTYwICAgICAzMTk5MCAgIDI2MjQ4ICAgIDQzODMgICA0NjQ1MiAgIDQyMTU2ICAgNjIzMjAgICA1MTA1MiAgIDI4NjIxCjAwMDAyMDAgICAgIDI3MjI2ICAgNjUyOTYgICA1NjMwNSAgIDMzMzc1ICAgIDQ4MTMgICA0MjI4MyAgIDE5OTgwICAgIDE5MjIKMDAwMDIyMCAgICAgNTcwNjEgICAyOTMyMiAgIDI3MDczICAgNjQ5ODYgICAxNTIxOSAgIDI2MjM0ICAgMjQxMDAgICAyMTIwNAonJycKCicnJwpDb3B5IHRoaXMgZmlsZSBhbmQgcnVuIGBwYnBhc3RlIHwgYmFzZTY0YCB0byBnZW5lcmF0ZSBjaGFsbGVuZ2UgdGV4dC4gQ29waW91cwp3aGl0ZSBzcGFjZSBpcyBhdCB0aGUgYm90dG9tIG9mIHRoZSBmaWxlIHRvIGVuc3VyZSB0cmFpbGluZyBgPT1gIGFuZCBoaW50IGF0CmJhc2U2NC4KJycnCgppbXBvcnQgY29kZWNzCmltcG9ydCBzdHJpbmcKaW1wb3J0IHN5cwppbXBvcnQgdGltZQoKZnJvbSBjcnlwdG9ncmFwaHkuaGF6bWF0LmJhY2tlbmRzIGltcG9ydCBkZWZhdWx0X2JhY2tlbmQKZnJvbSBjcnlwdG9ncmFwaHkuaGF6bWF0LnByaW1pdGl2ZXMuaGFzaGVzIGltcG9ydCBTSEExCmZyb20gY3J5cHRvZ3JhcGh5Lmhhem1hdC5wcmltaXRpdmVzLnR3b2ZhY3Rvci50b3RwIGltcG9ydCBUT1RQCgoKT05FX1dFRUtfSU5fU0VDT05EUyA9IDYwNF84MDAKCgpkZWYgZ2VuZXJhdGVfc2VjcmV0KCk6CiAgICB0b3RwID0gVE9UUCgKICAgICAgICBrZXk9Y29kZWNzLmVuY29kZShzdHJpbmcuYXNjaWlfbGV0dGVycywgZW5jb2Rpbmc9InV0Zi04IiksCiAgICAgICAgbGVuZ3RoPTgsCiAgICAgICAgYWxnb3JpdGhtPVNIQTEoKSwKICAgICAgICB0aW1lX3N0ZXA9T05FX1dFRUtfSU5fU0VDT05EUywKICAgICAgICBiYWNrZW5kPWRlZmF1bHRfYmFja2VuZCgpLAogICAgKQogICAgc2VlZCA9IGludCh0aW1lLnRpbWUoKSkKICAgIHRva2VuID0gY29kZWNzLmRlY29kZSh0b3RwLmdlbmVyYXRlKHNlZWQpLCBlbmNvZGluZz0idXRmLTgiKQogICAgcmV0dXJuIGYie3Rva2VufS17c2VlZH0iCgoKaWYgX19uYW1lX18gPT0gIl9fbWFpbl9fIjoKICAgIHN5cy5zdGRvdXQud3JpdGUoCiAgICAgICAgZiJQbGVhc2UgaGVhZCB0byBodHRwczovL3JhbXAuY29tL2NhcmVlcnMgYW5kIHVzZSB0aGlzIHNlY3JldCB3aGVuICIKICAgICAgICBmInlvdSBhcHBseToge2dlbmVyYXRlX3NlY3JldCgpfVxuIgogICAgKQoKCg==")
  
  (-> (java.util.Base64/getDecoder)
      (.decode the-thing)
      (str))
  
  (defn maybe-decode [s]
    (-> (java.util.Base64/getDecoder)
        (.decode s)))

  (with-open [input-stream (-> (.getBytes the-thing)
                               (maybe-decode)
                               io/input-stream)]
    (slurp input-stream))
  
  )