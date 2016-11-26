package com.jackoder.auto.bundle.compiler.writer;

import com.jackoder.auto.bundle.AutoBundle;
import com.jackoder.auto.bundle.IAutoBundle;
import com.jackoder.auto.bundle.compiler.IProcessor;
import com.jackoder.auto.bundle.compiler.helper.ClassProvider;
import com.jackoder.auto.bundle.compiler.holder.BundleHolder;
import com.jackoder.auto.bundle.compiler.holder.ExtraHolder;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.List;

import javax.lang.model.element.Modifier;

import static com.squareup.javapoet.MethodSpec.methodBuilder;

/**
 * @author Jackoder
 * @version 2016/11/25
 */
public class BundleWriter {

    BundleHolder mBundleHolder;
    IProcessor   mProcessor;

    public BundleWriter(BundleHolder bundleHolder, IProcessor processor) {
        mBundleHolder = bundleHolder;
        mProcessor = processor;
    }

    public void write() throws IOException {
        ClassName generatedClassName = ClassName.get(mBundleHolder.getPackageName(),
                AutoBundle.PREFIX + mBundleHolder.getClassName().simpleName().toString());
        final TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder(generatedClassName)
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(
                        ClassName.get(IAutoBundle.class), mBundleHolder.getClassName()));
        mBundleHolder.getExtras().forEach(extra -> typeSpecBuilder.addField(getField(extra)));
        typeSpecBuilder.addMethod(getReadMethod(mBundleHolder.getClassName(), mBundleHolder.getExtras()));
        typeSpecBuilder.addMethod(getWriteMethod(mBundleHolder.getClassName(), mBundleHolder.getExtras()));
        mBundleHolder.getExtras().forEach(extra ->
                typeSpecBuilder.addMethod(getFieldBuilderMethod(generatedClassName, extra)));
        typeSpecBuilder.addMethod(getEmptyBuildMethod());
        typeSpecBuilder.addMethod(getBuilderMethod(mBundleHolder.getExtras()));
        JavaFile javaFile = JavaFile.builder(mBundleHolder.getPackageName(), typeSpecBuilder.build()).build();
        javaFile.writeTo(mProcessor.filer());
    }

    private FieldSpec getField(ExtraHolder extraHolder) {
        return extraHolder.asField(Modifier.PROTECTED);
    }

    private MethodSpec getReadMethod(ClassName typeClassName, List<ExtraHolder> extraHolders) {
        MethodSpec.Builder builder = methodBuilder(IAutoBundle.METHOD_READ)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(ClassProvider.BUNDLE, "bundle")
                .addParameter(typeClassName, "target");
        for (ExtraHolder extraHolder : extraHolders) {
            builder.addStatement("target.$N = bundle.$N($S)",
                    extraHolder.getBundleFieldName(), extraHolder.getGetMethod(), extraHolder.getKey());
        }
        return builder.build();
    }

    private MethodSpec getWriteMethod(ClassName typeClassName, List<ExtraHolder> extraHolders) {
        MethodSpec.Builder builder = methodBuilder(IAutoBundle.METHOD_WRITE)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(typeClassName, "target")
                .addParameter(ClassProvider.BUNDLE, "bundle");
        for (ExtraHolder extraHolder : extraHolders) {
            builder.addStatement("bundle.$N($S, target.$N)",
                    extraHolder.getPutMethod(), extraHolder.getKey(), extraHolder.getBundleFieldName());
        }
        return builder.build();
    }

    private MethodSpec getFieldBuilderMethod(ClassName generatedClassName, ExtraHolder extraHolder) {
        return MethodSpec.methodBuilder(extraHolder.getFieldName())
                .addModifiers(Modifier.PUBLIC)
                .addParameter(extraHolder.asParameter())
                .addStatement("this.$N = $N", extraHolder.getFieldName(), extraHolder.getFieldName())
                .addStatement("return this")
                .returns(generatedClassName).build();
    }

    private MethodSpec getEmptyBuildMethod() {
        return MethodSpec.methodBuilder(IAutoBundle.METHOD_BUILD)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addStatement("return $N(null)", IAutoBundle.METHOD_BUILD)
                .returns(ClassProvider.BUNDLE).build();
    }

    private MethodSpec getBuilderMethod(List<ExtraHolder> extraHolders) {
        MethodSpec.Builder builder = MethodSpec.methodBuilder(IAutoBundle.METHOD_BUILD)
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(ClassProvider.BUNDLE, "bundle")
                .beginControlFlow("if (bundle == null)")
                .addStatement("bundle = new $T()", ClassProvider.BUNDLE)
                .endControlFlow();
        for (ExtraHolder extraHolder : extraHolders) {
            builder.addStatement("bundle.$N($S, $N)",
                    extraHolder.getPutMethod(), extraHolder.getKey(), extraHolder.getFieldName());
        }
        builder.addStatement("return bundle");
        builder.returns(ClassProvider.BUNDLE).build();
        return builder.build();
    }
}
