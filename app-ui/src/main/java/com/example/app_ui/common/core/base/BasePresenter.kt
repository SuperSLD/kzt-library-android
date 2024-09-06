package online.jutter.supersld.common.base

import com.arellomobile.mvp.MvpPresenter
import com.arellomobile.mvp.MvpView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import com.example.app_ui.common.core.base.CiceroneHolder
import com.example.app_domain.controllers.datacontrol.CompositeDisposable
import com.example.app_domain.controllers.datacontrol.Disposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.terrakok.cicerone.Router

/**
 * Базовый класс презентера.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
open class BasePresenter<V : MvpView> : MvpPresenter<V>(), CoroutineScope, KoinComponent {

    private val job = SupervisorJob()

    override val coroutineContext = Dispatchers.Main + job

    protected val navigationHolder: CiceroneHolder by inject()

    private val compositeDisposable = CompositeDisposable()

    protected val router: Router?
        get() = navigationHolder.currentRouter

    override fun onDestroy() {
        job.cancel()
        compositeDisposable.dispose()
        super.onDestroy()
    }

    protected fun Disposable.connect() {
        compositeDisposable.add(this)
    }
}