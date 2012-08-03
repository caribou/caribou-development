{:logging {:log-pattern  "%p %l %m%n"
           :log-level    :debug}
 :database {:classname    "org.h2.Driver"
            :subprotocol  "h2"
            :host         "localhost"
            :database     "caribou_development"
            :user         "h2"
            :password     ""} 
 :controller-ns  "skel.controllers"}
