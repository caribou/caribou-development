{:logging {:log-pattern "%d %-5p %u [%t]: %m %F %L%n"
           :log-level :warn}
 :database {:classname    "org.postgresql.Driver"
            :subprotocol  "postgresql"
            :host         "localhost"
            :database     "caribou_production"
            :user         "postgres"
            :password     "postgres"}
 :asset-dir      "assets"}
