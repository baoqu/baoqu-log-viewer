(ns viewer.db
  "This namespace contains all the functions used to extract data from
  the `sqlite` database. The queries are read from the `queries.sql`
  file in the `resources` folder, so they are isolated and easy to
  modify directly in `SQL` without any other layer in between our
  clojure code and it."
  (:require [hugsql.core :as hugsql]))

(hugsql/def-db-fns "sql/queries.sql")

(defn get-config
  "This method receives the path of the database file and returns a
  map with its configuration ready to be used with `hugsql`."
  [path]
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname path})

(defn run-query
  "We define a general function to run any query and then we define
  partial applications of this function for any given case."
  [query path]
  (-> path
      (get-config)
      (query)))

(def get-all-comments (partial run-query q-get-all-comments))

(def get-all-ideas (partial run-query q-get-all-ideas))

(def get-all-votes (partial run-query q-get-all-votes))
