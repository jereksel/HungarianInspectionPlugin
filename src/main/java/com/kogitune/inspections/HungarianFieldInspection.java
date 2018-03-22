package com.kogitune.inspections;

import com.intellij.codeInsight.daemon.impl.quickfix.RenameElementFix;
import com.intellij.codeInspection.BaseJavaLocalInspectionTool;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.psi.JavaElementVisitor;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiField;
import org.jetbrains.annotations.NotNull;

public class HungarianFieldInspection extends BaseJavaLocalInspectionTool {
    public HungarianFieldInspection() {
        super();
    }

    @NotNull
    @Override
    public PsiElementVisitor buildVisitor(@NotNull final ProblemsHolder holder, boolean isOnTheFly) {
        return new JavaElementVisitor() {
            @Override
            public void visitField(PsiField field) {
                super.visitField(field);
                mAlertIfHungarian(holder, field);
                m_mAlertIfHungarian(holder, field);
            }
        };
    }

    private void mAlertIfHungarian(ProblemsHolder holder, PsiField field) {
        final String fieldName = field.getName();
        if (fieldName == null) {
            return;
        }
        if (fieldName.matches("[ms][A-Z].*")) {
            String newFiledName = fieldName.substring(1, 2).toLowerCase() + fieldName.substring(2, fieldName.length());
            holder.registerProblem(field, "Hungary field name", new RenameElementFix(field, newFiledName));
        }
    }

    private void m_mAlertIfHungarian(ProblemsHolder holder, PsiField field) {
        final String fieldName = field.getName();
        if (fieldName == null) {
            return;
        }
        if (fieldName.matches("m_[a-z][A-Z].*")) {
            String newFiledName = fieldName.substring(3, 4).toLowerCase() + fieldName.substring(4, fieldName.length());
            holder.registerProblem(field, "Hungary field name", new RenameElementFix(field, newFiledName));
        }
    }

}
