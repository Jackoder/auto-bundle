package com.jackoder.auto.bundle.compiler.writer;

import com.jackoder.auto.bundle.AutoBundle;
import com.jackoder.auto.bundle.IAutoBundle;
import com.jackoder.auto.bundle.compiler.IProcessor;
import com.jackoder.auto.bundle.compiler.helper.ClassProvider;
import com.jackoder.auto.bundle.compiler.holder.BundleHolder;
import com.jackoder.auto.bundle.compiler.holder.ExtraHolder;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Modifier;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class BundleWritter {

    BundleHolder mBundleHolder;
    IProcessor   mProcessor;

    public BundleWritter(BundleHolder bundleHolder, IProcessor processor) {
        mBundleHolder = bundleHolder;
        mProcessor = processor;
    }

    public void write() {
        ClassName generatedClassName = ClassName.get(mBundleHolder.getPackageName(),
                AutoBundle.PREFIX + mBundleHolder.getClassName().simpleName().toString());
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(generatedClassName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(IAutoBundle.class);
        addFields(typeSpecBuilder, mBundleHolder.getExtras());
        addReadMethod(typeSpecBuilder, mBundleHolder.getClassName(), mBundleHolder.getExtras());
        addWriteMethod(typeSpecBuilder, mBundleHolder.getClassName(), mBundleHolder.getExtras());
    }

    private void addFields(TypeSpec.Builder typeSpecBuilder, List<ExtraHolder> extraHolders) {
        for (ExtraHolder extraHolder : extraHolders) {
            typeSpecBuilder.addField(extraHolder.getType(), extraHolder.getFiledName(), Modifier.PROTECTED);
        }
    }

    private void addReadMethod(TypeSpec.Builder typeSpecBuilder, ClassName typeClassName, List<ExtraHolder> extraHolders) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(IAutoBundle.METHOD_READ)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(ClassProvider.bundle, "bundle")
                .addParameter(typeClassName, "target");
        for (ExtraHolder extraHolder : extraHolders) {
            builder.addStatement("target.$N = bundle.$N(\"$N\")", extraHolder.getFiledName(), extraHolder.getGetMethod(), extraHolder.getKey());
        }
        typeSpecBuilder.addMethod(builder.build());
    }

    private void addWriteMethod(TypeSpec.Builder typeSpecBuilder, ClassName typeClassName, List<ExtraHolder> extraHolders) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(IAutoBundle.METHOD_WRITE)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(typeClassName, "target")
                .addParameter(ClassProvider.bundle, "bundle");
        for (ExtraHolder extraHolder : extraHolders) {
            builder.addStatement("bundle.$N(\"$N\", target.$N)",
                    extraHolder.getKey(), extraHolder.getPutMethod(), extraHolder.getFiledName());
        }
        typeSpecBuilder.addMethod(builder.build());
    }

    private void addFieldBuilder(TypeSpec.Builder typeSpecBuilder, ClassName generatedClassName, List<ExtraHolder> extraHolders) {

    }
}
