package com.jackoder.auto.bundle.compiler;

import com.google.auto.service.AutoService;
import com.jackoder.auto.bundle.ann.AutoBundle;
import com.jackoder.auto.bundle.ann.Extra;
import com.jackoder.auto.bundle.compiler.holder.BundleHolder;
import com.jackoder.auto.bundle.compiler.writer.BundleWriter;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic;

@AutoService(Processor.class)
public class BundleProcessor extends AbstractProcessor implements IProcessor {

    private Types    mTypeUtils;
    private Elements mElementUtils;
    private Filer    mFiler;

    private Messager mMessager;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        mTypeUtils = processingEnv.getTypeUtils();
        mElementUtils = processingEnv.getElementUtils();
        mFiler = processingEnv.getFiler();
        mMessager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> supportAnnotationTypes = new HashSet<>();
        supportAnnotationTypes.add(AutoBundle.class.getCanonicalName());
        supportAnnotationTypes.add(Extra.class.getCanonicalName());
        return supportAnnotationTypes;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        try {
            for (Element element : roundEnv.getElementsAnnotatedWith(AutoBundle.class)) {
                if (element.getKind() == ElementKind.CLASS) {
                    BundleHolder bundleHolder = new BundleHolder((TypeElement) element, this);
                    BundleWriter bundleWriter = new BundleWriter(bundleHolder, this);
                    bundleWriter.write();
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR,
                    "Exception while processing AutoBundle classes. Message:\n" + e.getMessage());
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Types typeUtils() {
        return mTypeUtils;
    }

    @Override
    public Elements elementUtils() {
        return mElementUtils;
    }

    @Override
    public Filer filer() {
        return mFiler;
    }

    @Override
    public Messager messager() {
        return mMessager;
    }
}
