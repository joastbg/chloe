(ns testing.gen)

; HTML-generator
; Johan Astborg 2012

; helpers
(defn m-reduce [l] (reduce str l))

(def stag (fn[t] (m-reduce ["<",t,">"])))
(def etag (fn[t] (m-reduce ["</",t,">"])))
(def mtag (fn[t,b] (m-reduce [(stag t),b,(etag t)])))

(defn multag-n [n] (fn [& args] (mtag n (m-reduce args))))
(defn unitag-n [n] (fn [s] (mtag n s)))

; tags
(def html (multag-n "html"))
(def head (multag-n "head"))
(def body (multag-n "body"))
(def title (multag-n "title"))
(def footer (multag-n "footer"))
(def p (multag-n "p"))

; h1-h6
(def h1 (unitag-n "h1"))
(def h2 (unitag-n "h2"))
(def h3 (unitag-n "h3"))
(def h4 (unitag-n "h4"))
(def h5 (unitag-n "h5"))
(def h6 (unitag-n "h6"))

; text-level semantics
(def b (unitag-n "b"))
(def i (unitag-n "i"))
(def big (unitag-n "big"))
(def mark (unitag-n "mark"))
(def small (unitag-n "small"))
(def strong (unitag-n "strong"))
(def strike (unitag-n "strike"))

; hr
(def hr (fn[] "<hr/>"))

; a href
(def ahref (fn[l,t] (m-reduce ["<a href='",l,"'>",t,"</a>"])))

; ul, ol and li
(def li (fn[s] (mtag "li" s)))

(defn ol [& args] (mtag "ol" (m-reduce (map li args))))
(defn ul [& args] (mtag "ul" (m-reduce (map li args))))