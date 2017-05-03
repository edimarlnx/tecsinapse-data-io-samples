package br.com.tecsinapse.data.io.samples;

import br.com.tecsinapse.exporter.Table;
import br.com.tecsinapse.exporter.TableCell;
import br.com.tecsinapse.exporter.style.TableCellStyle;
import br.com.tecsinapse.exporter.type.CellType;
import br.com.tecsinapse.exporter.util.ExporterUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

public class SimpleExportSample implements Sample {

    public static void main(String[] args) throws IOException {
        new SimpleExportSample().sample();
    }

    @Override
    public void sample() throws IOException {
        Table table = new Table();
        table.addNewRow();

        table.add("Ordem", TableCellStyle.HEADER);
        table.add("Data", TableCellStyle.HEADER);
        table.add("Hora", TableCellStyle.HEADER);
        table.add("Data Hora", TableCellStyle.HEADER);
        table.add("Nome", TableCellStyle.HEADER);

        table.addNewRow();
        table.add(1, TableCellStyle.BODY);
        table.add(toDate(LocalDate.now()));
        table.add(toDate(LocalTime.now()), CellType.TIME_TYPE);
        table.add(toDate(LocalDateTime.now()));
        table.add("Dennis Ritchie");

        table.addNewRow();
        table.add(2, TableCellStyle.BODY);
        table.add(toDate(LocalDate.now().minusDays(1)));
        table.add(new TableCell(toDate(LocalTime.now().minusHours(1))).withCellType(CellType.TIME_TYPE));
        table.add(toDate(LocalDateTime.now().minusDays(1).minusHours(2)));
        table.add("Richard Stallman");
        File destino = new File(getTestPath() + File.separatorChar + "EstiloPadraoTest.xlsx");
        File file = ExporterUtil.getXlsxFile(table, destino.getName());
        java.nio.file.Files.move(file.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

    private String getTestPath() {
        return SimpleExportSample.class.getResource("/").getFile();
    }

    private Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    private Date toDate(LocalDate localDate) {
        return toDate(localDate.atStartOfDay());
    }
    private Date toDate(LocalTime localTime) {
        return toDate(localTime.atDate(LocalDate.ofYearDay(1900,1)));
    }

}