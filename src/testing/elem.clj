(ns testing.elem
  (:refer-clojure :exclude (list get delete)))

(def elems (atom {}))

(defn list []
  @elems)

(defn get [id]
  (@elems id))

(defn put [id attrs]
  (let [new-attrs (merge (get id) attrs)]
    (swap! elems assoc id new-attrs)
    new-attrs))

(defn delete [id]
  (let [old-attrs (get id)]
    (swap! elems dissoc id)
    old-attrs))