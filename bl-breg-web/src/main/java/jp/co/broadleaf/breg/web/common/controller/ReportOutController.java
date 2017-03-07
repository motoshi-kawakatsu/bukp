//****************************************************************************//
// システム                                    : 優良部品簡易登録システムBREG
// ---------------------------------------------------------------------------//
//                (c) Copyright 2017 Broadleaf Co.,Ltd.
//============================================================================//
// 履歴
// ---------------------------------------------------------------------------//
// 管理番号                                   作成担当 : lulei
// 作 成 日       2017/02/08   修正内容 : 新規作成
// ---------------------------------------------------------------------------//
package jp.co.broadleaf.breg.web.common.controller;

import jp.co.broadleaf.breg.common.enums.ColumnKindEnum;
import jp.co.broadleaf.breg.common.enums.ReportKindEnum;
import jp.co.broadleaf.breg.common.model.ExportOutModel;
import jp.co.broadleaf.framework.web.WebResult;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.RectangleReadOnly;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * <pre>
 * 帳票出力送信スタブサービスクラス.
 * </pre>
 */
public class ReportOutController {

  /** PDPファイルサイズ字体 */
  private static final int FONT_SIZE_PDF = 11;
  /** ファイル保存位置 */

  private static final String FILE_OUT = "/usr/local/breg/tmp/";
  /** EXCELファイルサイズ字体 */
  private static final int FONT_SIZE_EXCEL = 11;
  /** フォント */
  private static final String FONT_DEFAULT = "ＭＳ ゴシック";
  /** ページ幅 */
  private static final float PAGE_WIDTH = 842F;
  /** ページ高 */
  private static final float PAGE_HEIGHT = 595F;

  /**
   * ファイル作成
   * 
   * @param exportOutModel ファイル内容model
   * @param response response
   * @param result result
   * @return true
   * @throws Exception Exception
   */
  public boolean reportOut(ExportOutModel exportOutModel, HttpServletResponse response,
                           WebResult result) throws Exception {
    String[] tableHeader = exportOutModel.getTableHeader();
    String[] colKind = exportOutModel.getColKind();
    List<String[]> detailList = exportOutModel.getDetailList();
    String reportName = exportOutModel.getReportName();
    int reportKind = exportOutModel.getReportKind();
    float[] colWidth = exportOutModel.getColWidth();
    if (ReportKindEnum.KindPdf.getValue() == reportKind) {
      pdfOut(tableHeader, colKind, detailList, reportName, colWidth);
    } else if (ReportKindEnum.KindExcel.getValue() == reportKind) {
      excelOut(tableHeader, colKind, detailList, reportName);
    } else if (ReportKindEnum.KindTsv.getValue() == reportKind) {
      tsvOut(tableHeader, detailList, reportName);
    }

    ArrayList<String> data = new ArrayList<>();
    data.add("success");
    data.add(FILE_OUT);
    data.add(exportOutModel.getReportName());
    result.put("data", data);

    return true;
  }

  /**
   * TSVファイル作成
   * 
   * @param tableHeader ファイルヘッド
   * @param detailList ファイル内容
   * @param fileName ファイル名
   * @throws IOException IOException
   */
  private void tsvOut(String[] tableHeader, List<String[]> detailList, String fileName) throws IOException {
    String fileNameFull = FILE_OUT + fileName;
    File f = new File(fileNameFull);
    if (!f.exists()) {
      f.createNewFile();
    }
    FileWriter fw = new FileWriter(f);
    PrintWriter pw = new PrintWriter(fw);
    int i = 1;
    for (String strings : tableHeader) {
      if (tableHeader.length == i) {
        pw.append(strings + "\r\n");
      } else {
        pw.append(strings + "\t");
      }
      i++;
    }
    i = 1;
    for (String[] strings : detailList) {
      for (String string : strings) {
        if (string == null) {
          string = "";
        }
        if (strings.length == i) {
          pw.append(string);
        } else {
          pw.append(string + "\t");
        }
        i++;
      }
      pw.append("\r\n");
    }

    pw.flush();
    pw.close();
  }

  /**
   * PDFファイル作成
   * 
   * @param tableHeader ファイルヘッド
   * @param colKind アイテムタイプ
   * @param detailList ファイル内容
   * @param fileName ファイル名
   * @param colWidth 項目長
   * @return ファイルの全経路
   * @throws Exception Exception
   */
  private String pdfOut(String[] tableHeader, String[] colKind, List<String[]> detailList, String fileName,
                        float[] colWidth) throws Exception {
    String fileNameFull = FILE_OUT + fileName;
    Document document = new Document(new RectangleReadOnly(PAGE_WIDTH, PAGE_HEIGHT));
    PdfWriter.getInstance(document, new FileOutputStream(fileNameFull));

    document.open();
    float colWidthSum = 0F;
    int startCol = 0;
    boolean lastOutFlg = false;
    for (int j = 0; j < colWidth.length;) {
      while (colWidthSum <= PAGE_WIDTH) {
        if (j >= colWidth.length) {
          document.add(createTable(startCol, colWidth.length - 1, tableHeader, colKind, detailList, colWidth));
          lastOutFlg = true;
          break;
        }
        colWidthSum += colWidth[j++];
      }
      if (lastOutFlg) {
        break;
      }
      j = j - 2;
      document.add(createTable(startCol, j, tableHeader, colKind, detailList, colWidth));
      document.newPage();
      colWidthSum = 0F;
      startCol = j + 1;
    }
    document.close();

    return fileNameFull;
  }

  /**
   * EXCELファイル作成
   * 
   * @param tableHeader ファイルヘッド
   * @param colKind アイテムタイプ
   * @param detailList ファイル内容
   * @param fileName ファイル名
   * @return ファイルの全経路
   * @throws Exception Exception
   */
  private String excelOut(String[] tableHeader, String[] colKind, List<String[]> detailList,
                          String fileName) throws Exception {
    String fileNameFull = FILE_OUT + fileName;
    HSSFWorkbook wb = new HSSFWorkbook();
    final int fontSize = wb.getNumberOfFonts();
    for (int i = 0; i < fontSize; i++) {
      wb.getFontAt((short) i).setFontHeightInPoints((short) FONT_SIZE_EXCEL);
      wb.getFontAt((short) i).setFontName(FONT_DEFAULT);
    }

    FileOutputStream fos = new FileOutputStream(fileNameFull);

    HSSFSheet sheet = wb.createSheet(fileName);
    initSheet(sheet);
    writeByRow(wb, sheet, tableHeader, colKind, detailList);
    wb.write(fos);
    fos.close();
    return fileNameFull;
  }

  /**
   * 作成PDF列を作成する
   * 
   * @param startCol 開始列
   * @param endCol 終わり列
   * @param tableHeader ファイルヘッド
   * @param colKind アイテムタイプ
   * @param detailList ファイル内容
   * @param colWidth 項目長
   * @return PdfPTable
   * @throws DocumentException DocumentException
   * @throws IOException IOException
   */
  private static PdfPTable createTable(int startCol, int endCol, String[] tableHeader, String[] colKind,
                                       List<String[]> detailList,
                                       float[] colWidth) throws DocumentException, IOException {
    float[] colWidthByPage = new float[endCol - startCol + 1];
    int index = 0;
    for (int i = startCol; i <= endCol; i++) {
      colWidthByPage[index++] = colWidth[i];
    }
    PdfPTable table = new PdfPTable(colWidthByPage);
    table.setWidthPercentage(100);

    // ヘッダ 出力
    for (int i = startCol; i <= endCol; i++) {
      table.addCell(createPdfCell(tableHeader[i], null));
    }
    // 明細出力
    for (String[] strArr : detailList) {
      for (int i = startCol; i <= endCol; i++) {
        table.addCell(createPdfCell(strArr[i], colKind[i]));
      }
    }
    return table;
  }

  /**
   * PDF列を作成する
   * 
   * @param str 内容
   * @param colKind アイテムタイプ
   * @return PdfPCell
   * @throws DocumentException DocumentException
   * @throws IOException IOException
   */
  private static PdfPCell createPdfCell(String str, String colKind) throws DocumentException, IOException {
    PdfPCell cell = new PdfPCell(getJapaneseFont(str));
    cell.setUseAscender(true);
    if (ColumnKindEnum.Rnumeric.getValue().equals(colKind)) {
      cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    } else if (ColumnKindEnum.Rstring.getValue().equals(colKind)) {
      cell.setHorizontalAlignment(Element.ALIGN_LEFT);
    } else {
      cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    }
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
    return cell;
  }

  /**
   * 文字のフォーマットを設定して
   * 
   * @param str パス
   * @return Paragraph
   * @throws DocumentException DocumentException
   * @throws IOException IOException
   */
  private static Paragraph getJapaneseFont(String str) throws DocumentException, IOException {
    BaseFont bfJapanese = BaseFont.createFont("msgothic.ttc,1", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
    Font fontJapanese = new Font(bfJapanese, FONT_SIZE_PDF, Font.NORMAL);
    Paragraph pragraph = new Paragraph(str, fontJapanese);
    return pragraph;
  }

  /**
   * 表の初期化
   * 
   * @param sheet sheet
   */
  private static void initSheet(HSSFSheet sheet) {
    sheet.setMargin(HSSFSheet.TopMargin, 0.6692913385826772);
    sheet.setMargin(HSSFSheet.BottomMargin, 0.5118110236220472);
    sheet.setMargin(HSSFSheet.LeftMargin, 0.5905511811023623);
    sheet.setMargin(HSSFSheet.RightMargin, 0.5905511811023623);
    HSSFPrintSetup printSetup = sheet.getPrintSetup();
    printSetup.setLandscape(false);
    printSetup.setPaperSize(HSSFPrintSetup.A4_PAPERSIZE);
    printSetup.setHResolution((short) 600);
    printSetup.setLeftToRight(true);
  }

  /**
   * 書込み内容
   * 
   * @param wb wb
   * @param sheet sheet
   * @param tableHeader ファイルヘッド
   * @param colKind アイテムタイプ
   * @param detailList ファイル内容
   */
  private static void writeByRow(HSSFWorkbook wb, HSSFSheet sheet, String[] tableHeader, String[] colKind,
                                 List<String[]> detailList) {
    int lineIndex = 0;
    // タイトル行
    HSSFRow rowTitle = sheet.createRow(lineIndex++);
    writeBycell(wb, rowTitle, tableHeader, null);
    // 明細行
    for (String[] detail : detailList) {
      HSSFRow row = sheet.createRow(lineIndex++);
      writeBycell(wb, row, detail, colKind);
    }

    for (int i = 0; i < tableHeader.length; i++) {
      sheet.autoSizeColumn((short) i);
    }
  }

  /**
   * 書込み内容
   * 
   * @param wb wb
   * @param row row
   * @param detail ファイル内容
   * @param colKind アイテムタイプ
   */
  private static void writeBycell(HSSFWorkbook wb, HSSFRow row, String[] detail, String[] colKind) {
    int column = 0;
    for (String str : detail) {
      HSSFCell cell = row.createCell(column++);
      HSSFCellStyle cellStyle = cell.getCellStyle();
      HSSFCellStyle cellStyleNew = wb.createCellStyle();
      cellStyleNew.cloneStyleFrom(cellStyle);
      if (colKind == null) {
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cellStyleNew.setAlignment(HSSFCellStyle.ALIGN_CENTER);
      } else if (colKind.length >= column && ColumnKindEnum.Rnumeric.getValue().equals(colKind[column - 1])) {
        // 数値型
        cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
        cellStyleNew.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
      } else {
        // 文字型
        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
        cellStyleNew.setAlignment(HSSFCellStyle.ALIGN_LEFT);
      }
      cell.setCellValue(str);
      cell.setCellStyle(cellStyleNew);
    }
  }

}
