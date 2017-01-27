/*
 * 
 * 
 *  La classe Clendrier correspond à nos besoin et a été trouvée sur internet avec quelques modifications apportées
 * 
 * 
 * 
 * */
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class Calendrier extends JPanel  {

	private GregorianCalendar _calendar = new GregorianCalendar();

	private DateFormatSymbols _dateSymbols = new DateFormatSymbols();

	private SimpleDateFormat _formatMY = new SimpleDateFormat("MMMM yyyy");

	private int _firstDayOfWeek = _calendar.getFirstDayOfWeek();

	private final JLabel _monthYear = new JLabel("", SwingUtilities.CENTER);

	private final JLabel[] _daysOfWeek = new JLabel[7];

	private final JButton[] _daysNumber = new JButton[42];

	private final ActionListener _changeMonth = new ActionListener() {
		public void actionPerformed(final ActionEvent ae) {
			final int nb = "next".equals(ae.getActionCommand()) ? 1 : -1;
			_calendar.add(Calendar.MONTH, nb);
			updateMonthYear();
			updateDaysNumber();
		}
	};

	public Calendrier() {
		setLayout(new BorderLayout());
		// Month Panel
		final JPanel monthPanel = new JPanel();
		final JButton previous = new JButton("<<");
		previous.addActionListener(_changeMonth);
		final JButton next = new JButton(">>");
		next.addActionListener(_changeMonth);
		next.setActionCommand("next");
		_monthYear.setPreferredSize(new Dimension(320, 120));
		monthPanel.add(previous);
		monthPanel.add(_monthYear);
		monthPanel.add(next);
		add(monthPanel, BorderLayout.NORTH);
		// Day Panel

		final JPanel dayPanel = new JPanel();

		dayPanel.setLayout(new GridLayout(7, 7));
		for (int i = 0; i < 7; ++i)
			dayPanel.add(_daysOfWeek[i] = new JLabel("", SwingUtilities.CENTER));
		for (int i = 0; i < _daysNumber.length; ++i)
			dayPanel.add(_daysNumber[i] = new JButton());
		add(dayPanel, BorderLayout.CENTER);

		// Remplissage des composants
		updateMonthYear();
		updateDaysOfWeek();
		updateDaysNumber();
	}

	// Affiche le mois et l'année en cours
	private void updateMonthYear() {
		_monthYear.setText(_formatMY.format(_calendar.getTime()));
	}

	// Affiche les jours de la semaine
	private void updateDaysOfWeek() {
		final String[] weekDays = _dateSymbols.getShortWeekdays();
		for (int i = 1; i < weekDays.length; ++i) {
			final int index = (i - 2 + _firstDayOfWeek) % 7 + 1;
			_daysOfWeek[i - 1].setText(weekDays[index]);
		}
	}

	// Affiche le numéro des jours
	private void updateDaysNumber() {
		// apparition des marches dans le mois
		String var = "";
		int M1 = 2;
		int M2 = 3;
		int M3 = 4;
		int M4 = 6;

		final Date tmp = _calendar.getTime();
		_calendar.set(Calendar.DAY_OF_MONTH, 1);
		final int firstDay = _calendar.get(Calendar.DAY_OF_WEEK);
		final int LocalFirstDay = (firstDay - _firstDayOfWeek + 7) % 7 + 1;
		boolean full = false;
		for (int i = 0; i < _daysNumber.length; ++i) {
			// Détermine si le composant est affiché ou non
			final boolean isNotEmpty = i < LocalFirstDay - 1 || full;
			_daysNumber[i].setVisible(!isNotEmpty);

			// Affichage du jour
			if (!isNotEmpty) {
				final int dayOfMonth = _calendar.get(Calendar.DAY_OF_MONTH);
				// apparitions et frequences des marches

				if (dayOfMonth == M1) {
					var = var + "A";
					M1 += 3;
				}
				if (dayOfMonth == M2) {
					var = var + "B";
					M2 += 4;
				}
				if (dayOfMonth == M3) {
					var = var + "C";
					M3 += 5;
				}
				if (dayOfMonth == M4) {
					var = var + "D";
					M4 += 7;// frequences ....
				}

				Day day = new Day(_calendar.getTime());
				_daysNumber[i].setAction(day);
				_daysNumber[i].setText(day.toString());
				var = "";// retourne vide apres chaque execution

				_calendar.add(Calendar.DAY_OF_MONTH, 1);
				full = 1 == _calendar.get(Calendar.DAY_OF_MONTH);
			}

		}
		_calendar.setTime(tmp);
	}


	public class Day extends AbstractAction{
		private Date date;

		public Day(Date date){
			this.date=date;

		}

		@Override
		public String toString() {
			return new SimpleDateFormat("d").format(date);
		}

		@Override
		//ici que l'on modifie le messsage renvoyé
		public void actionPerformed(ActionEvent e) {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			String dayName = calendar.getDisplayName(GregorianCalendar.DAY_OF_WEEK, GregorianCalendar.LONG, new Locale("fr"));
			dayName=dayName.substring(0,1).toUpperCase()+dayName.substring(1); 
			JOptionPane.showMessageDialog(null,dayName);

		}
	}


}