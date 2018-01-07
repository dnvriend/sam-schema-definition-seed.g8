package $package$

import com.github.dnvriend.sam.serialization.annotation.SamSchema

@SamSchema
case class Person(
                   name: String = "",
                   age: Int = 0,
                   address: Address = Address(),
                 )

case class Address(
                    street: String = "",
                    houseNr: Int = 0,

                  )

@SamSchema
case class Order(
                  ordernr: Int = 0,
                  lines: List[OrderLine] = List.empty[OrderLine],
                )

case class OrderLine(
                    item: String = "",
                    stock: Int = 0,
                    amount: Int = 0,
                    )