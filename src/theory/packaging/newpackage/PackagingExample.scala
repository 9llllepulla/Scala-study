/**
 * Пакетирование.
 *
 * Один из способов размещения кода в пакеты в одном файле.
 */
package theory.packaging {

  package newpackage {

    /** Импорт классов в пакет с возможностью сокрытия выбранного класса (c помощью _ )*/
    //import theory.constructions.{BasicIntQueue => _, Doubling}

    /** Импорт классов в пакет с возможностью переименования выбранного класса (с указанием нового имени) */
    import theory.constructions.{BasicIntQueue => RenamedBasicIntQueue, Doubling}

    // в пакете theory.packaging.newpackage
    object PackagingExample extends App {

      /**
       * Теперь можно воспользоваться переименованным классом
       * Для модификатора private или protected можно указывать область защиты в []
       * можно указать как определенный класс, так и пакет
       * например:
       * private[newpackage] val exampleRenaming = new RenamedBasicIntQueue with Doubling
       */
      private[PackagingExample] val exampleRenaming = new RenamedBasicIntQueue with Doubling
      exampleRenaming.put(22)
      println(exampleRenaming.get()) // 44

    }

    package test {

      // в пакете theory.packaging.newpackage.test
      class PackagingExampleTest

    }
  }
}


