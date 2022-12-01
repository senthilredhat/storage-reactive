package com.brightly.storage.utility;

import io.quarkus.arc.Priority;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Priority(9999)
@Interceptor
@FGAExceptionInterceptor
public class ExceptionInterceptor {

    @Inject
    FGAClientSynchronizer clientSynchronizer;

    @AroundInvoke
    public Object interceptTenantId(InvocationContext ctx) throws Exception {
        try {
            System.out.println("##### interceptor Called ###########");
            return ctx.proceed();
        } catch (RuntimeException ex){
            System.out.println("##### In Exception Block ###########");
            clientSynchronizer.undoMessages();
            throw  ex;
        }
    }

}
