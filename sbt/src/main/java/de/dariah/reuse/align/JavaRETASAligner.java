package de.dariah.reuse.align;

import edu.umass.ciir.AlignedSequence;
import edu.umass.ciir.RecursiveAlignmentTool;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * JavaRETASAligner
 */
public class JavaRETASAligner implements Aligner<File> {

    protected void writeResult(File res, ArrayList<String> references, ArrayList<String> candidates) {
        try {
            FileWriter wrt = new FileWriter(res);
            Iterator<String> refIt = references.iterator();
            StringBuilder refString = new StringBuilder();
            if (refIt.hasNext()) refString.append(refIt.next());
            while (refIt.hasNext()) refString.append('\t').append(refIt.next());
            Iterator<String> canIt = candidates.iterator();
            StringBuilder canString = new StringBuilder();
            if (canIt.hasNext()) canString.append(canIt.next());
            while (canIt.hasNext()) canString.append('\t').append(canIt.next());
            wrt.write(refString.toString()+"\n"+canString.toString());
            wrt.flush(); wrt.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    @Override
    public File align(File src, File trg, File tmp) {
        ArrayList<AlignedSequence> array = RecursiveAlignmentTool.processSingleJob_getAlignedSequence(src.getAbsolutePath(), trg.getAbsolutePath(), "", "");
        ArrayList<String> references = new ArrayList<String>();
        ArrayList<String> candidates = new ArrayList<String>();
        for (AlignedSequence a : array) {
            references.add(a.getReference());
            candidates.add(a.getCandidate());
        }

        String srcID = src.getName().replaceAll("\\.[^\\.]+$","");
        String trgID = trg.getName().replaceAll("\\.[^\\.]+$","");;
        String resID = srcID + "@" + trgID;

        File resTmpFile = new File(tmp,resID+".txt");
        writeResult(resTmpFile,references,candidates);

        return resTmpFile;
    }
}
