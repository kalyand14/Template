package com.android.basics.di;

import androidx.appcompat.app.AppCompatActivity;

import com.android.basics.core.di.InstanceContainer;
import com.android.basics.core.navigation.BundleFactory;
import com.android.basics.core.navigation.IntentFactory;
import com.android.basics.core.navigation.Navigator;
import com.android.basics.domain.repository.UserRepository;

public class ApplicationScope {

    private final InstanceContainer container = new InstanceContainer();
    private static ApplicationScope instance = null;

    private UserScope userScope;

    private ApplicationModule module;

    public ApplicationModule getModule() {
        return module;
    }

    public void setModule(ApplicationModule module) {
        this.module = module;
    }

    private ApplicationScope() {
    }

    public static ApplicationScope getInstance() {
        if (instance == null) {
            instance = new ApplicationScope();
        }
        return instance;
    }

    public IntentFactory intentFactory() {
        if (!container.has(IntentFactory.class)) {
            container.register(IntentFactory.class, module.provideIntentFactory());
        }
        return container.get(IntentFactory.class);
    }

    public BundleFactory bundleFactory() {
        if (!container.has(BundleFactory.class)) {
            container.register(BundleFactory.class, module.provideBundleFactory());
        }
        return container.get(BundleFactory.class);
    }

    public Navigator navigator(AppCompatActivity activity) {
        return module.provideNavigator(activity, intentFactory(), bundleFactory());
    }

    public UserRepository userRepository() {
        if (!container.has(UserRepository.class)) {
            container.register(UserRepository.class, module.provideUserRepository(module.provideDaoExecutor(), module.provideUserDao(module.provideTodoDatabase(module.getApplication())), module.provideUserMapper()));
        }
        return container.get(UserRepository.class);
    }

    public UserScope getUserScope() {
        return userScope;
    }

    public void setUserScope(UserScope userScope) {
        this.userScope = userScope;
    }

}
