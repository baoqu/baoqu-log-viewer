(ns viewer.generator
  (:require [viewer.db :as db]))

(defn generate-log
  [path]
  (println (map :body (db/get-all-comments path))))
