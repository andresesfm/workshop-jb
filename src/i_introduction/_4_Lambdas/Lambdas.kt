package i_introduction._4_Lambdas

import com.google.common.base.Predicate
import com.google.common.collect.Iterables
import util.*

fun todoTask3(collection: Collection<Int>): Nothing = TODO(
        """
        Task 3.
        Rewrite 'JavaCode3.task3()' in Kotlin using lambdas.
        You can find the appropriate function to call on 'collection' through IntelliJ's code completion feature.
        (Don't use the class 'Iterables').
    """,
        documentation = doc3(),
        references = { JavaCode3().task3(collection) })

fun task3(collection: Collection<Int>): Boolean = collection.any { it -> it % 42 == 0 };





