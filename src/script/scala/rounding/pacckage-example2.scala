/**
  * Created by Cao Wei Dong on 2018-01-14.
  */
package com {
	package example {
		package pkg1 {

			class Class11 {
				def m = "m11"
			}

			class Class12 {
				def m = "m12"
			}

		}

		package pgk2 {

			class Class21 {
				def m = "m21"

				def makeClass11 = {
					new pkg1.Class11
				}
				def makeClass12 = {
					enw pgk1.Class12
				}
			}

		}

		package pkg3.pkg31.pkg311{
			class Class311{
				def m = "m21"
			}
		}

	}

}

