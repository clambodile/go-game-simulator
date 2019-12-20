(defproject go_simulator "0.1.0-SNAPSHOT"
  :description "go simulation (in progress)"
  :url "N/A"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [instaparse "1.4.10"]
                 [rhizome "0.2.9"]
                 [org.clojure/core.match "0.3.0"]]
  :repl-options {:init-ns go-simulator.core})
