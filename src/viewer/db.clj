(ns viewer.db
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/queries.sql")

(defn get-config
  "This method receives the path of the database file and returns a
  map with its configuration ready to be used with `hugsql`"
  [path]
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname path})

(defn get-all-comments
  [path]
  (-> path
      (get-config)
      (q-get-all-comments)))

(defn get-all-ideas
  [path]
  (-> path
      (get-config)
      (q-get-all-ideas)))
