package exercises.conecenter

import java.io.File

/**
 * В двадцатые годы эстонский учёный Kuuse Kood разработал алгоритм для передачи
 * структурированных сообщений. Его изобретение позволяет принимать получателю
 * неструктурированный (плоский) набор элементов и преобразовывать его в дерево имея у себя
 * ключ шифра (код).
 * Для нашей задачи упростим условие. Нам будет приходить последовательность заглавных букв
 * английского алфавита. Наша задача, используя код, преобразовать последовательность в дерево.
 *
 * Требуется написать программу, которая считывает из файла code.in код, записанный в
 * схематичном виде. Из файла message.in берёт сообщение и создаёт новый файл message.out
 * содержащий сообщение в схематичном виде, если сообщение может быть разобрано при
 * помощи кода. Если сообщение не может быть разобрано, то записывает строку “ Vigane sõnum”.
 * Программа должна быть реализована на языке Scala. В качестве бонуса реализуйте решение
 * используя только неизменяемые (immutable) переменные и коллекции.
 *
 * A( B(C, C(A) ), D(A) )
 */
object Main {
  def main(args: Array[String]): Unit = {

    /**
     * 1. Сканируем файл с кодом из code.in в список строк
     * 2. Строим из списка строк дерево символов - это ключ шифра
     *
     * 3. Сканируем файл из message.in в строку сообщения
     * 4. Декодируем сообщение по дереву ключа шифра в список символов
     * 5. Строим дерево декодированного сообщения.
     * 6. Записываем дерево в файл
     *
     * */

    val fileIO = FileIO
    val firstFileLines = fileIO.readAsLines(new File(args.apply(0)))
    val secondFileLines = fileIO.readAsLines(new File(args.apply(1)))

    val decoder = MessageDecoder
    val decodeMessageAsLines = decoder.decodeMessage(firstFileLines, secondFileLines)
    val fileName = "message.out"

    fileIO.save(decodeMessageAsLines, fileName)
  }
}
