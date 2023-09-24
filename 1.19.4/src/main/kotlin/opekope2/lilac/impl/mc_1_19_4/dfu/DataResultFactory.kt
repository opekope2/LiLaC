package opekope2.lilac.impl.mc_1_19_4.dfu

import com.mojang.serialization.DataResult
import com.mojang.serialization.Lifecycle
import opekope2.lilac.api.dfu.IDataResultFactory
import java.util.function.Supplier

class DataResultFactory : IDataResultFactory {
    override fun <T : Any> success(result: T): DataResult<T> =
        DataResult.success(result)

    override fun <T : Any> success(result: T, lifecycle: Lifecycle): DataResult<T> =
        DataResult.success(result, lifecycle)

    override fun <T : Any> error(message: Supplier<String>): DataResult<T> =
        DataResult.error(message)

    override fun <T : Any> error(message: Supplier<String>, partialResult: T): DataResult<T> =
        DataResult.error(message, partialResult)

    override fun <T : Any> error(message: Supplier<String>, lifecycle: Lifecycle): DataResult<T> =
        DataResult.error(message, lifecycle)

    override fun <T : Any> error(message: Supplier<String>, partialResult: T, lifecycle: Lifecycle): DataResult<T> =
        DataResult.error(message, partialResult, lifecycle)
}
